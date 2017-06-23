
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

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
