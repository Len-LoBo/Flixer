package com.lenlobo.flixer.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String short_overview;
    String full_overview;
    float rating;
    int id;

    // empty constructor need for Parceler library
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        full_overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = (float) jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");

        // ensure short overview is only 150 characters max
        if (full_overview.length() > 150) short_overview = full_overview.substring(0, 150) + "...";
        else short_overview = full_overview;
    }

    public Movie(String posterPath, String backdropPath, String title, String short_overview, String full_overview, float rating, int id) {
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.title = title;
        this.short_overview = short_overview;
        this.full_overview = full_overview;
        this.rating = rating;
        this.id = id;
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getShortOverview() {
        return short_overview;
    }

    public String getFullOverview() { return full_overview; }

    public float getRating() {return rating;}

    public int getId() {return id;}
}


