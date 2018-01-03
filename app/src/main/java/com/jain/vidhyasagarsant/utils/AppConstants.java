package com.jain.vidhyasagarsant.utils;

/**
 * Created by @iamBedant on 15/03/17.
 */

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "mindorks_mvp.db";
    public static final String PREF_NAME = "mindorks_pref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String CATEGORY_ID = "_category_id";

    public static final String URL = "Url";
    public static final String CATEGORY_NAME = "name";

    public static final String WEBVIEW_CONTENT = "WebView_Content";
    public static final String WEBVIEW_TITLE = "WebView_Title";

    public static final String GALLARY_RESPONSE = "Gallary_Response";
    public static final String GALLARY_IMAGE_INDEX = "Gallary_Image_Index";
    public static final String PREF_CATEGORY = "_pref_category";
    public static final String PREF_SUBCATEGORY = "_pref_subcategory";
    public static final String PREF_SUBCATEGORY_ITEM = "_pref_subcategory_item";
    public static final String DEFAULT_CATEGORY = "";
    public static final String SUB_CATEGORY_ID = "_sub_category_id";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
