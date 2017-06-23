package com.jain.vidhyasagarsant.ui.contactUs;

import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by kuliza-319 on 7/6/17.
 */

public class ContactUsPresenter<V extends ContactUsMvpView> extends BasePresenter<V>
        implements ContactUsMvpPresenter<V>{

    @Inject
    public ContactUsPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void phoneClicked(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        getMvpView().call(intent);
    }

    @Override
    public void emailClicked(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.setPackage("com.google.android.gm");
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        getMvpView().sendEmail(emailIntent);
    }
}
