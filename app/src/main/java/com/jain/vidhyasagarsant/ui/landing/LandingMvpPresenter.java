package com.jain.vidhyasagarsant.ui.landing;


import android.content.Context;

import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.ui.base.MvpPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;

/**
 * Created by @iamBedant on 15/03/17.
 */

@PerActivity
public interface LandingMvpPresenter<V extends LandingMvpView> extends MvpPresenter<V> {


    void NavItemClicked(int nav_item);

    void getCategoryCollection();


    void dashboardClicked(Category.Response response);

}
