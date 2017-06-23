package com.jain.vidhyasagarsant.ui.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kuliza-303 on 07/06/17.
 */

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    @BindView(com.jain.vidhyasagarsant.R.id.gallery_image)
    ImageView mGalleryImage;

    @BindView(com.jain.vidhyasagarsant.R.id.image_description)
    TextView mImageDescription;

    View itemView;

    public GalleryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.itemView = itemView;
    }

    public ImageView getmGalleryImage() {
        return mGalleryImage;
    }

    public void setmGalleryImage(ImageView mGalleryImage) {
        this.mGalleryImage = mGalleryImage;
    }

    public TextView getmImageDescription() {
        return mImageDescription;
    }

    public void setmImageDescription(TextView mImageDescription) {
        this.mImageDescription = mImageDescription;
    }

    public View getItemView() {
        return itemView;
    }
}
