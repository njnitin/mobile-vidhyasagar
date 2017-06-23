package com.jain.vidhyasagarsant.ui.categoryList.master;

import com.jain.vidhyasagarsant.ui.base.MvpPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;

/**
 * Created by @iamBedant on 01/06/17.
 */

@PerActivity
public interface CategoryItemListMvpPresenter<V extends CategoryItemListMvpView> extends MvpPresenter<V> {
    void getCategoryItemData(String categoryId);

    void getSubCategoryItemData(String categoryId, String subCategoryId);
}
