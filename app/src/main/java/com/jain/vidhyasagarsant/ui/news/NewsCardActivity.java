package com.jain.vidhyasagarsant.ui.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.CustomChrome.CustomTabsHelper;
import com.jain.vidhyasagarsant.ui.base.BaseActivity;
import com.jain.vidhyasagarsant.ui.customUiComponent.VerticalViewPager;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

/**
 * Created by kuliza-303 on 31/05/17.
 */

public class NewsCardActivity extends BaseActivity implements NewsCardMvpView {

    @Inject
    NewsCardMvpPresenter<NewsCardMvpView> mPresenter;

    VerticalViewPager verticalViewPager;

    CustomTabsIntent customTabsIntent;

    CustomTabsClient mClient;
    CustomTabsSession mCustomTabsSession;

    String msubCategoryId, mCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_description_card);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(NewsCardActivity.this);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.NEWS_CARD_SCREEN_VISITED);

        mCategoryId = getIntent().getExtras().getString(AppConstants.CATEGORY_ID);
        msubCategoryId = getIntent().getExtras().getString(AppConstants.SUB_CATEGORY_ID);


        if(msubCategoryId==null || msubCategoryId.isEmpty()) {
            mPresenter.getCategoryItemData(mCategoryId);
        }else {
            mPresenter.getSubCategoryItemData(mCategoryId,msubCategoryId);
        }


        initChromeCustomTab();
    }

    public void initChromeCustomTab() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(getSession());
        builder.setToolbarColor(ContextCompat.getColor(this, com.jain.vidhyasagarsant.R.color.black)).setShowTitle(true);
        // prepareMenuItems(builder);
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), com.jain.vidhyasagarsant.R.drawable.back_icon));
        //  prepareActionButton(builder);
//        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
//        builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);

        customTabsIntent = builder.build();
        CustomTabsHelper.addKeepAliveExtra(this, customTabsIntent.intent);
    }

    private CustomTabsSession getSession() {
        if (mClient == null) {
            mCustomTabsSession = null;
        } else if (mCustomTabsSession == null) {
            mCustomTabsSession = mClient.newSession(new NavigationCallback());
        }
        return mCustomTabsSession;
    }

    private static class NavigationCallback extends CustomTabsCallback {
        @Override
        public void onNavigationEvent(int navigationEvent, Bundle extras) {
            Log.w("TAG", "onNavigationEvent: Code = " + navigationEvent);
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void setData(List<CategoryItemData.Response> response) {
        verticalViewPager = (VerticalViewPager) findViewById(com.jain.vidhyasagarsant.R.id.verticleViewPager);
        verticalViewPager.setAdapter(new VerticlePagerAdapter(this,response,callBack));
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.NEWS_CARDS_LOADED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    public void launchUrl(Context mContext, String url) {
        customTabsIntent.launchUrl((Activity) mContext, Uri.parse(url));
    }

    VerticlePagerAdapter.CallBack callBack = new VerticlePagerAdapter.CallBack() {
        @Override
        public void LaunchUrl(String url) {
            launchUrl(NewsCardActivity.this,url);
        }
    };
}
