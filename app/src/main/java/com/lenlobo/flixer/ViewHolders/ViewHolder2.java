package com.lenlobo.flixer.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenlobo.flixer.R;

public class ViewHolder2 extends RecyclerView.ViewHolder {

    RelativeLayout container;
    ImageView ivPoster;


    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        ivPoster = itemView.findViewById(R.id.ivPoster);
        container = itemView.findViewById(R.id.container);
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView ivPoster) {
        this.ivPoster = ivPoster;
    }

    public RelativeLayout getContainer() {
        return container;
    }
}
