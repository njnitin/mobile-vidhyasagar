
package com.jain.vidhyasagarsant.data.remote;
import android.content.Context;
import android.text.TextUtils;

import com.jain.vidhyasagarsant.data.local.json.JsonParserHelper;
import com.jain.vidhyasagarsant.data.local.prefs.AppPreferencesHelper;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.gallery.GalleryOutputModel;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;
//import com.jain.vidhyasagarsant.data.local.json.JsonParserHelper;

import io.reactivex.Observable;
import com.google.gson.Gson;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
//    private JsonParserHelper jsonParserHelper;
    Gson gson = new Gson();

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<SubCategories> getSubCategories(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.SUBCATEGORY_URL+id)
                .build()
                .getObjectObservable(SubCategories.class);
    }

    public Observable<CategoryItemData> getCategoryItemData(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORY_ITEM_URL+id)
                .build()
                .getObjectObservable(CategoryItemData.class);
    }

    @Override
    public Observable<GalleryOutputModel> getGalleryImageList(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORY_ITEM_URL+id)
                .build()
                .getObjectObservable(GalleryOutputModel.class);
    }

    @Override
    public Observable getSubCategoryItemData(String categoryId, String subCategoryId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORY_ITEM_URL+categoryId+"&sub_category_id="+subCategoryId)
                .build()
                .getObjectObservable(CategoryItemData.class);
    }

    public Observable<Category> getCategories() {
//    public Observable getCategories() {
//       return Observable.fromArray(gson.fromJson(jsonParserHelper.loadAsset("content.json"), Category.class));

/*        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORY_URL)
                .build()
                .getObjectObservable(Category.class);*/

        final String json = JsonParserHelper.getInstance().loadFromAsset("content.json");
        if(!TextUtils.isEmpty(json)){
            Gson gson = new Gson();
            Category categorydata = gson.fromJson(json, Category.class);
            return Observable.just(categorydata);
        }else{
            return Observable.just(null);
        }

//        AppPreferencesHelper aa=new AppPreferencesHelper();
//        return aa.getCategoryFromDisk();
//        return aa.getContent(myContext,"a","a");

/*        Category cc;
        cc = gson.fromJson(jsonParserHelper.loadAsset("content.json"), Category.class);*/
//        return jsonParserHelper.loadAsset("content.json");
    }

}


