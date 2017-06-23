package com.jain.vidhyasagarsant.ui.contactUs;

import com.jain.vidhyasagarsant.ui.base.MvpPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;

/**
 * Created by kuliza-319 on 7/6/17.
 */
@PerActivity
public interface ContactUsMvpPresenter<V extends ContactUsMvpView> extends MvpPresenter<V> {

    void phoneClicked(String phoneNumber);

    void emailClicked(String email);
}
