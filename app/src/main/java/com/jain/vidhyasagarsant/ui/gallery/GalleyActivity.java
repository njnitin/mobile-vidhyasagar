package com.jain.vidhyasagarsant.ui.gallery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;

/**
 * Created by kuliza-303 on 07/06/17.
 */

public class GalleyActivity extends BaseActivity implements GalleryMvpView {

    @Inject
    GalleryMvpPresenter<GalleryMvpView> mPresenter;

    @BindView(com.jain.vidhyasagarsant.R.id.gallery_list)
    RecyclerView mGalleryView;
    String mCategoryId;
    String msubCategoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_gallery_layout);
        setUp();
        mCategoryId = getIntent().getExtras().getString(AppConstants.CATEGORY_ID);
        msubCategoryId = getIntent().getExtras().getString(AppConstants.SUB_CATEGORY_ID);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.GALLERY_SCREEN_VISITED);

        if(msubCategoryId==null || msubCategoryId.isEmpty()) {
            mPresenter.getCategoryItemData(mCategoryId);
        }else {
            mPresenter.getSubCategoryItemData(mCategoryId,msubCategoryId);
        }
    }


    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);


    }

    @Override
    public void setData(List<CategoryItemData.Response> response) {
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.GALLERY_LOADED);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        mGalleryView.setHasFixedSize(true);
        mGalleryView.setLayoutManager(gridLayoutManager);
        mGalleryView.setAdapter(new GalleryAdapter(this,response));
    }
}
