package com.jain.vidhyasagarsant.injection.module;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.jain.vidhyasagarsant.injection.ActivityContext;
import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListMvpPresenter;
import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListPresenter;
import com.jain.vidhyasagarsant.ui.contactUs.ContactUsMvpView;
import com.jain.vidhyasagarsant.ui.contactUs.ContactUsPresenter;
import com.jain.vidhyasagarsant.ui.developerProfile.DeveloperMvpPresenter;
import com.jain.vidhyasagarsant.ui.developerProfile.DeveloperPresenter;
import com.jain.vidhyasagarsant.ui.gallery.GalleryMvpPresenter;
import com.jain.vidhyasagarsant.ui.gallery.GalleryMvpView;
import com.jain.vidhyasagarsant.ui.imageFullScreenActivity.ImageFullScreenMvpPresenter;
import com.jain.vidhyasagarsant.ui.imageFullScreenActivity.ImageFullScreenPresenter;
import com.jain.vidhyasagarsant.ui.landing.LandingMvpPresenter;
import com.jain.vidhyasagarsant.ui.landing.LandingPresenter;
import com.jain.vidhyasagarsant.ui.news.NewsCardMvpPresenter;
import com.jain.vidhyasagarsant.ui.news.NewsCardPresenter;
import com.jain.vidhyasagarsant.ui.subCategory.master.SubCategoryListMvpPresenter;
import com.jain.vidhyasagarsant.ui.subCategory.master.SubCategoryListPresenter;
import com.jain.vidhyasagarsant.ui.videoList.VideoListMvpView;
import com.jain.vidhyasagarsant.ui.videoList.VideoListPresenter;
import com.jain.vidhyasagarsant.injection.PerActivity;
import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListMvpView;
import com.jain.vidhyasagarsant.ui.contactUs.ContactUsMvpPresenter;
import com.jain.vidhyasagarsant.ui.news.NewsCardMvpView;
import com.jain.vidhyasagarsant.ui.developerProfile.DeveloperMvpView;
import com.jain.vidhyasagarsant.ui.gallery.GalleryPresenter;
import com.jain.vidhyasagarsant.ui.imageFullScreenActivity.ImageFullScreenMvpView;
import com.jain.vidhyasagarsant.ui.landing.LandingMvpView;

import dagger.Module;
import dagger.Provides;

import com.jain.vidhyasagarsant.ui.videoList.VideoListMvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import com.jain.vidhyasagarsant.ui.subCategory.master.SubCategoryListMvpView;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    LandingMvpPresenter<LandingMvpView> provideLandingPresenter(
            LandingPresenter<LandingMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    NewsCardMvpPresenter<NewsCardMvpView> provideDescriptionCardPresenter(
            NewsCardPresenter<NewsCardMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SubCategoryListMvpPresenter<SubCategoryListMvpView> provideItemListPresenter(
            SubCategoryListPresenter<SubCategoryListMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CategoryItemListMvpPresenter<CategoryItemListMvpView> provideCategoryItemListPresenter(
            CategoryItemListPresenter<CategoryItemListMvpView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    ContactUsMvpPresenter<ContactUsMvpView> provideContactUsPresenter(
            ContactUsPresenter<ContactUsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DeveloperMvpPresenter<DeveloperMvpView> provideDeveloperPresenter(
            DeveloperPresenter<DeveloperMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    GalleryMvpPresenter<GalleryMvpView> provideGalleryPresenter(
            GalleryPresenter<GalleryMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ImageFullScreenMvpPresenter<ImageFullScreenMvpView> provideImageFullScreenPresenter(
            ImageFullScreenPresenter<ImageFullScreenMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    VideoListMvpPresenter<VideoListMvpView> provideVideoListPresenter(
            VideoListPresenter<VideoListMvpView> presenter) {
        return presenter;
    }
}