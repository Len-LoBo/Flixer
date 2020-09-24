package com.lenlobo.flixer.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenlobo.flixer.R;

public class ViewHolder1 extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;


    public ViewHolder1(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle){
        this.tvTitle = tvTitle;
    }

    public TextView getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(TextView tvOverview) {
        this.tvOverview = tvOverview;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView ivPoster) {
        this.ivPoster = ivPoster;
    }
}
