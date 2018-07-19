
package com.jain.vidhyasagarsant.data.remote;

import com.jain.vidhyasagarsant.BuildConfig;

/**
 * Created by @iamBedant on 15/03/17.
 */

public final class ApiEndPoint {

    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String PASSWORD = BuildConfig.PASSWORD;


    public static final String SUBCATEGORY_URL = BASE_URL+"get_subcategories?category_id=";

    public static final String CATEGORY_ITEM_URL = BASE_URL+"get_items?category_id=";

    public static final String CATEGORY_URL = BASE_URL+"get_categories/";

    public static final String FLASHNEWS_URL = BASE_URL+"get_flash_news/";

    public static final String VIHARUPDATES_URL = BASE_URL+"get_vihar_updates/";

    public static final String LASTUPDATETIME_URL = BASE_URL+"get_last_update_time/";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
