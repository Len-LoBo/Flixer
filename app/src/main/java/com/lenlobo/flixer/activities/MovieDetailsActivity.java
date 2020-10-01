package com.lenlobo.flixer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lenlobo.flixer.BuildConfig;
import com.lenlobo.flixer.MovieHttpClient;
import com.lenlobo.flixer.R;
import com.lenlobo.flixer.databinding.ActivityMovieDetailsBinding;
import com.lenlobo.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    private ActivityMovieDetailsBinding binding;

    TextView tvTitle;
    TextView tvOverview;
    TextView tvReleaseDate;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        tvReleaseDate = binding.tvRelease;
        ratingBar = binding.ratingBar;
        youTubePlayerView = binding.player;


        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        assert movie != null;
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getFullOverview());
        ratingBar.setRating(movie.getRating());
        tvReleaseDate.setText("Release Date: " + reformatDate(movie.getReleaseDate()));


        MovieHttpClient.getTrailer(movie.getId(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {

                try {
                    JSONObject jsonObject = json.jsonObject;
                    JSONArray results = jsonObject.getJSONArray("results");
                    if (results.length() == 0) return;
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    initializeYoutube(youtubeKey);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d("MovieDetailsActivity", "onFailure");

            }
        });

    }

    private void initializeYoutube(final String youtubeKey) {
        youTubePlayerView.initialize(BuildConfig.YoutubeApiKey, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(youtubeKey);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailsActivity", "onInitializationFailur");

            }
        });
    }

    private String reformatDate(String dateString) {
        if (dateString == "") return "N/A";

        String year = dateString.substring(0, 4);
        String month = dateString.substring(5, 7);
        String day = dateString.substring(8,10);

        return month + "/" + day + "/" + year;

    }
}