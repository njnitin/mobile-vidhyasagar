package com.jain.vidhyasagarsant.ui.subCategory.master;

import com.jain.vidhyasagarsant.data.remote.model.subcategories.Response;
import com.jain.vidhyasagarsant.ui.base.MvpPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;

/**
 * Created by @iamBedant on 01/06/17.
 */

@PerActivity
public interface SubCategoryListMvpPresenter<V extends SubCategoryListMvpView> extends MvpPresenter<V> {
    void getCategoryData(String categoryId);

    void itemClicked(Response mItem);
}
