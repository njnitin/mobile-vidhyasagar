package com.jain.vidhyasagarsant.data;


import android.content.Context;

import com.jain.vidhyasagarsant.data.local.prefs.PreferencesHelper;
import com.jain.vidhyasagarsant.data.remote.ApiHeader;
import com.jain.vidhyasagarsant.data.remote.ApiHelper;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.gallery.GalleryOutputModel;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;
import com.jain.vidhyasagarsant.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@Singleton
public class AppDataManager implements DataManager{
    private static final String TAG = "AppDataManager";

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }

    @Override
    public Observable<SubCategories> getSubCategories(String id) {
        return mApiHelper.getSubCategories(id);
    }

    @Override
    public Observable<CategoryItemData> getCategoryItemData(String id) {
        return mApiHelper.getCategoryItemData(id);
    }

    @Override
    public Observable<GalleryOutputModel> getGalleryImageList(String id) {
        return mApiHelper.getGalleryImageList(id);
    }

    @Override
    public Observable getSubCategoryItemData(String categoryId, String subCategoryId) {
        return mApiHelper.getSubCategoryItemData(categoryId,subCategoryId);
    }


    @Override
    public Observable<Category> getCategories() {
            return Observable.mergeDelayError(
               getCategoryFromDisk(),
               mApiHelper.getCategories().doOnNext(new Consumer<Category>() {
                    @Override
                    public void accept(Category category) throws Exception {
                        Timber.d("Saving Data to cache");
                        saveCategoryToDisk(category);
                    }
               })
       );
    }

    @Override
    public Observable<Category> getCategoryFromDisk() {
        Timber.d("getting data from cache");
        return mPreferencesHelper.getCategoryFromDisk();
    }

    @Override
    public void saveCategoryToDisk(Category category) {
        mPreferencesHelper.saveCategoryToDisk(category);
    }
}
