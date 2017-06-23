package com.jain.vidhyasagarsant.ui.videoList;

import com.jain.vidhyasagarsant.ui.base.MvpPresenter;

/**
 * Created by kuliza-319 on 15/6/17.
 */

public interface VideoListMvpPresenter<V extends VideoListMvpView> extends MvpPresenter<V> {
    void getCategoryItemData(String categoryId);

    void getSubCategoryItemData(String categoryId, String subcategoryId);
}
