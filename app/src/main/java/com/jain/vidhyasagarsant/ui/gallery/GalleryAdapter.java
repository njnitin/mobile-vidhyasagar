package com.jain.vidhyasagarsant.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.imageFullScreenActivity.ImageFullScreenActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import io.iamBedant.starter.utils.GoogleAnalyticsManager;

/**
 * Created by kuliza-303 on 07/06/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder>{

    private Context mContext;
    private List<CategoryItemData.Response> mList;

    public GalleryAdapter(Context mContext, List<CategoryItemData.Response> response) {
        this.mContext = mContext;
        this.mList = response;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(com.jain.vidhyasagarsant.R.layout.gallery_item_layout, viewGroup, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder galleryViewHolder, final int position) {
        Glide.with(mContext).load(mList.get(position).getLink())
                .into(galleryViewHolder.getmGalleryImage());
        
        galleryViewHolder.getmImageDescription().setText(mList.get(position).getDescription());
        galleryViewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.GALLERY_IMAGE_CLICKED);
                Intent intent = new Intent(mContext, ImageFullScreenActivity.class);
                intent.putParcelableArrayListExtra(AppConstants.GALLARY_RESPONSE, new ArrayList<CategoryItemData.Response>(mList));
                intent.putExtra(AppConstants.GALLARY_IMAGE_INDEX, position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
