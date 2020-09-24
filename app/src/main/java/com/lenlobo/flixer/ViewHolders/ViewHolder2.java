package com.lenlobo.flixer.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenlobo.flixer.R;

public class ViewHolder2 extends RecyclerView.ViewHolder {

    ImageView ivPoster;


    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView ivPoster) {
        this.ivPoster = ivPoster;
    }
}
