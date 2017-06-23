package io.iamBedant.starter.utils;

import com.google.android.gms.analytics.HitBuilders;
import com.jain.vidhyasagarsant.App;


/**
 * Created by kuliza-303 on 16/05/17.
 */

public class GoogleAnalyticsManager {

    // Google Analytics Events

    // Landing Screen events
    public static final String LANDING_SCREEN_VISITED = "Landing_Screen_Visited";
    public static final String LANDING_SCREEN_CATEGORY_LOADED = "Landing_Screen_Category_Loaded";

    // News Screen events
    public static final String NEWS_CARD_SCREEN_VISITED = "News_Card_Screen_Visited";
    public static final String NEWS_CARDS_LOADED = "News_Cards_Loaded";

    // Category Screen events
    public static final String CATEGORY_SCREEN_VISITED = "Category_Screen_Visited";
    public static final String CATEGORIES_LOADED = "Categories_Loaded";

    // Category Item Detail Screen events
    public static final String CATEGORY_ITEM_DETAILS_SCREEN_VISITED = "Category_Item_Details_Screen_Visited";
    public static final String CATEGORY_ITEM_DETAILS_LOADED = "Category_Item_Details_Loaded";

    // Contact Us Screen events
    public static final String CONTACT_US_VISITED= "Contact_Us_Screen_Visited";
    public static final String CONTACT_CALL_CLICKED = "Contact_Call_Process_Initiated";
    public static final String CONTACT_EMAIL_CLICKED = "Contact_Email_Process_Initiated";

    // Developers Screen events
    public static final String DEVELOPERS_SCREEN_VISITED= "Developers_Screen_Visited";
    public static final String DEVELOPERS_SOCIAL_FB = "Developers_Social_FB_Profile_Opened";
    public static final String DEVELOPERS_SOCIAL_IN = "Developers_Social_IN_Profile_Opened"; // IN = Linkedin

    // Gallery Screen events
    public static final String GALLERY_SCREEN_VISITED= "Gallery_Screen_Visited";
    public static final String GALLERY_LOADED= "Gallery_Loaded";
    public static final String GALLERY_IMAGE_CLICKED= "Gallery_Image_Clicked";

    // Image Full Screen events
    public static final String IMAGE_FULL_SCREEN_VISITED= "Image_Full_Screen_Visited";

    // Notification Screen events
    public static final String NOTIFICATION_SCREEN_VISITED= "Notification_Screen_Visited";

    // Sub Category Screen events
    public static final String SUB_CATEGORY_SCREEN_VISITED = "Sub_Category_Screen_Visited";
    public static final String SUB_CATEGORIES_LOADED = "Sub_Categories_Loaded";

    // Sub Category Item Detail Screen events
    public static final String SUB_CATEGORY_ITEM_DETAILS_SCREEN_VISITED = "Sub_Category_Item_Details_Screen_Visited";
    public static final String SUB_CATEGORY_ITEM_DETAILS_LOADED = "Sub_Category_Item_Details_Loaded";

    // Video Screen events
    public static final String VIDEO_SCREEN_VISITED = "Video_Screen_Visited";
    public static final String VIDEO_SCREEN_LOADED = "Video_Screen_Loaded";

    // Video Screen events
    public static final String WEBVIEW_SCREEN_VISITED = "Webview_Screen_Visited";

    public static void trackGoogleAnalyticsEvent(String label) {
        App.getsInstance().getDefaultTracker().send(new HitBuilders
                .EventBuilder()
                .setCategory("App")
                .setValue(1)
                .setLabel(label)
                .build());
    }
}
