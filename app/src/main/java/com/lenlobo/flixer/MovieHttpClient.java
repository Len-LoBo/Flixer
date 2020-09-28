package com.lenlobo.flixer;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.lenlobo.flixer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.Headers;

public class MovieHttpClient {

    private static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key="  + BuildConfig.ApiKey + "&language=en-US&page=";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getNowPlaying(int pageIndex, JsonHttpResponseHandler responseHandler) {
        client.get(NOW_PLAYING_URL + pageIndex, responseHandler);
    }




}
