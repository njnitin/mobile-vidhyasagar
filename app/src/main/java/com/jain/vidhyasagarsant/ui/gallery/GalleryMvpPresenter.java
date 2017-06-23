package com.jain.vidhyasagarsant.ui.gallery;

import com.jain.vidhyasagarsant.injection.PerActivity;
import com.jain.vidhyasagarsant.ui.base.MvpPresenter;

/**
 * Created by kuliza-303 on 07/06/17.
 */


@PerActivity
public interface GalleryMvpPresenter<V extends GalleryMvpView> extends MvpPresenter<V> {

    void getCategoryItemData(String categoryId);

    void getSubCategoryItemData(String categoryId, String subCategoryId);
}