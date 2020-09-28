package com.lenlobo.flixer.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lenlobo.flixer.R;
import com.lenlobo.flixer.ViewHolders.ViewHolder1;
import com.lenlobo.flixer.ViewHolders.ViewHolder2;
import com.lenlobo.flixer.activities.MovieDetailsActivity;
import com.lenlobo.flixer.activities.MovieListActivity;
import com.lenlobo.flixer.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;

    private final int POPULAR = 0, UNPOPULAR = 1;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");

        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case POPULAR:
                View popularView = LayoutInflater.from(context).inflate(R.layout.item_popular_movie, parent, false);
                viewHolder = new ViewHolder2(popularView);
                break;
            default:
                View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolder1(movieView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        switch (holder.getItemViewType()) {
            case POPULAR:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getRating() > 7.0f) {
            return 0;
        } else {
            return 1;
        }
    }

    public void configureViewHolder1(final ViewHolder1 vh, final int position) {
        vh.getTvTitle().setText(movies.get(position).getTitle());
        vh.getTvOverview().setText(movies.get(position).getShortOverview());
        int camera_placeholder;

        String imageUrl;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageUrl = movies.get(position).getBackdropPath();
            camera_placeholder = R.drawable.camera_wide;

        } else {
            imageUrl = movies.get(position).getPosterPath();
            camera_placeholder = R.drawable.camera;

        }

        Glide.with(context)
                .load(imageUrl)
                .placeholder(camera_placeholder)
                .into(vh.getIvPoster());

        vh.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MovieDetailsActivity.class);
                Pair<View, String> p1 = Pair.create((View) vh.getTvTitle(), "title");
                Pair<View, String> p2 = Pair.create((View) vh.getTvOverview(), "overview");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, p1, p2);
                i.putExtra("movie", Parcels.wrap(movies.get(position)));
                context.startActivity(i, options.toBundle());
                //Toast.makeText(context, movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void configureViewHolder2(ViewHolder2 vh, final int position) {

        int camera_placeholder = R.drawable.camera_wide;

        Glide.with(context)
                .load(movies.get(position).getBackdropPath())
                .placeholder(camera_placeholder)
                .into(vh.getIvPoster());

        vh.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MovieDetailsActivity.class);
                i.putExtra("movie", Parcels.wrap(movies.get(position)));
                context.startActivity(i);
                //Toast.makeText(context, movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}




