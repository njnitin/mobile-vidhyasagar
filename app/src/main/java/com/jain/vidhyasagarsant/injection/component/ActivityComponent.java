
package com.jain.vidhyasagarsant.injection.component;

import com.jain.vidhyasagarsant.ui.categoryList.master.CategoryItemListActivity;
import com.jain.vidhyasagarsant.ui.contactUs.ContactUsActivity;
import com.jain.vidhyasagarsant.ui.subCategory.master.SubCategoryListActivity;
import com.jain.vidhyasagarsant.injection.module.ActivityModule;
import com.jain.vidhyasagarsant.ui.news.NewsCardActivity;
import com.jain.vidhyasagarsant.injection.PerActivity;
import com.jain.vidhyasagarsant.ui.developerProfile.DeveloperActivity;
import com.jain.vidhyasagarsant.ui.gallery.GalleyActivity;
import com.jain.vidhyasagarsant.ui.imageFullScreenActivity.ImageFullScreenActivity;
import com.jain.vidhyasagarsant.ui.landing.LandingActivity;
import dagger.Component;

import com.jain.vidhyasagarsant.ui.videoList.VideoListActivity;
import com.jain.vidhyasagarsant.ui.webView.WebViewActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LandingActivity landingActivity);
    void inject(SubCategoryListActivity subCategoryListActivity);
    void inject(NewsCardActivity newsCardActivity);
    void inject(ContactUsActivity contactUsActivity);
    void inject(DeveloperActivity developerActivity);
    void inject(GalleyActivity galleyActivity);
    void inject(WebViewActivity webViewActivity);
    void inject(CategoryItemListActivity categoryItemListActivity);
    void inject(ImageFullScreenActivity imageFullScreenActivity);
    void inject(VideoListActivity videoListActivity);
}
