
package com.jain.vidhyasagarsant.data.remote;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.gallery.GalleryOutputModel;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

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
        return Rx2AndroidNetworking.get(ApiEndPoint.CATEGORY_URL)
                .build()
                .getObjectObservable(Category.class);
    }
}

