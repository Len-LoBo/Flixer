package com.lenlobo.flixer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.lenlobo.flixer.MovieHttpClient;
import com.lenlobo.flixer.R;
import com.lenlobo.flixer.adapters.MovieAdapter;
import com.lenlobo.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MovieListActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        //create adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        //setup adapter on recycler view
        rvMovies.setAdapter(movieAdapter);

        //set layout manager on recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        for (int pageIndex = 1; pageIndex < 3; pageIndex++) {
            MovieHttpClient.getNowPlaying(pageIndex, new JsonHttpResponseHandler() {
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
}