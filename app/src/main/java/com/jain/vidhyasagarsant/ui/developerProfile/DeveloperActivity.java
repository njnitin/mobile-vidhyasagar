package com.jain.vidhyasagarsant.ui.developerProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jain.vidhyasagarsant.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

public class DeveloperActivity extends BaseActivity implements DeveloperMvpView {

    @Inject
    DeveloperMvpPresenter<DeveloperMvpView> mPresenter;

    @BindView(com.jain.vidhyasagarsant.R.id.toolbar)
    Toolbar mToolbar;

    private String mHeading = "Developers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_developer);
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SCREEN_VISITED);
        setUp();
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(DeveloperActivity.this);

        mToolbar.setTitle(mHeading);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({com.jain.vidhyasagarsant.R.id.bt_fb_abhishek, com.jain.vidhyasagarsant.R.id.bt_fb_rajesh, com.jain.vidhyasagarsant.R.id.bt_fb_ansu, com.jain.vidhyasagarsant.R.id.bt_fb_ansu1, com.jain.vidhyasagarsant.R.id.bt_fb_bedanta, com.jain.vidhyasagarsant.R.id.bt_fb_raj, com.jain.vidhyasagarsant.R.id.bt_fb_rakesh,
            com.jain.vidhyasagarsant.R.id.bt_in_abhisek, com.jain.vidhyasagarsant.R.id.bt_in_ansu, com.jain.vidhyasagarsant.R.id.bt_in_ansu1, com.jain.vidhyasagarsant.R.id.bt_in_bedanta, com.jain.vidhyasagarsant.R.id.bt_in_raj, com.jain.vidhyasagarsant.R.id.bt_in_rakesh})
    void socialIconClicked(View v){
        Intent browserIntent = null;
        switch (v.getId()){
            case com.jain.vidhyasagarsant.R.id.bt_fb_rajesh:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/lrde.rajesh"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_abhishek:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/abhishek.mittal.79219"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_ansu:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ansu.jn"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_ansu1:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ansu.jn"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_bedanta:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/iamBedant"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_raj:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100006523120386"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_fb_rakesh:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/winnerkm"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_FB);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_abhisek:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/abhishek-mittal-97530ab1/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_ansu:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ansujain/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_ansu1:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ansujain/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_bedanta:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/iambedant/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_raj:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rajpatel039/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;
            case com.jain.vidhyasagarsant.R.id.bt_in_rakesh:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/winnerkm/"));
                GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.DEVELOPERS_SOCIAL_IN);
                break;

        }
        startActivity(browserIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
