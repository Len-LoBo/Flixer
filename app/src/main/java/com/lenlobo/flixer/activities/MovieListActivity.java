package com.lenlobo.flixer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.lenlobo.flixer.MovieHttpClient;
import com.lenlobo.flixer.R;
import com.lenlobo.flixer.adapters.MovieAdapter;
import com.lenlobo.flixer.databinding.ActivityMovieListBinding;
import com.lenlobo.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MovieListActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ActivityMovieListBinding binding;

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) binding.toolbar;
        setSupportActionBar(toolbar);

        RecyclerView rvMovies = binding.rvMovies;
        movies = new ArrayList<>();

        //create adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        //setup adapter on recycler view
        rvMovies.setAdapter(movieAdapter);

        //set layout manager on recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));


        MovieHttpClient.getNowPlaying(1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG, "Json exception", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");

            }
        });

    }
}