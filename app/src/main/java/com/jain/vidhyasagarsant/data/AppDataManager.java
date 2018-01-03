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
    public Observable<?> getContent(Context myContext, String categoryType, String fileName){
        return getCategoryFromDisk();
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

    @Override
    public Observable<SubCategories> getSubCategories(String id) {
        return Observable.mergeDelayError(
                getSubCategoryFromDisk(),
                mApiHelper.getSubCategories(id).doOnNext(new Consumer<SubCategories>() {
                    @Override
                    public void accept(SubCategories subCategories) throws Exception {
                        Timber.d("Saving Data to cache");
                        saveSubCategoryToDisk(subCategories);
                    }
                })
        );
    }

    @Override
    public Observable<SubCategories> getSubCategoryFromDisk() {
        Timber.d("getting data from cache");
        return mPreferencesHelper.getSubCategoryFromDisk();
    }

    @Override
    public void saveSubCategoryToDisk(SubCategories subcategory) {
        mPreferencesHelper.saveSubCategoryToDisk(subcategory);
    }


    @Override
    public Observable getSubCategoryItemData(String categoryId, String subCategoryId) {
        return Observable.mergeDelayError(
                getSubCategoryItemFromDisk(),
                mApiHelper.getSubCategoryItemData(categoryId,subCategoryId).doOnNext(new Consumer<CategoryItemData>() {
                    @Override
                    public void accept(CategoryItemData categoryItemData) throws Exception {
                        Timber.d("Saving Data to cache");
                        saveSubCategoryItemToDisk(categoryItemData);
                    }
                })
        );
    }

    @Override
    public Observable<CategoryItemData> getSubCategoryItemFromDisk() {
        Timber.d("getting data from cache");
        return mPreferencesHelper.getSubCategoryItemFromDisk();
    }

    @Override
    public void saveSubCategoryItemToDisk(CategoryItemData subcategory) {
        mPreferencesHelper.saveSubCategoryItemToDisk(subcategory);
    }

}
