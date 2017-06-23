package com.jain.vidhyasagarsant.ui.news;


import com.jain.vidhyasagarsant.ui.base.MvpPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;

/**
 * Created by kuliza-303 on 31/05/17.
 */

@PerActivity
public interface NewsCardMvpPresenter<V extends NewsCardMvpView> extends MvpPresenter<V> {

    void getCategoryItemData(String categoryId);

    void getSubCategoryItemData(String mCategoryId, String msubCategoryId);
}
