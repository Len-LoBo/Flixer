package com.lenlobo.flixer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.lenlobo.flixer.BuildConfig;
import com.lenlobo.flixer.MovieHttpClient;
import com.lenlobo.flixer.R;
import com.lenlobo.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class TrailerActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        youTubePlayerView = findViewById(R.id.player);

        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

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
                youTubePlayer.loadVideo(youtubeKey);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailsActivity", "onInitializationFailur");

            }
        });
    }
}