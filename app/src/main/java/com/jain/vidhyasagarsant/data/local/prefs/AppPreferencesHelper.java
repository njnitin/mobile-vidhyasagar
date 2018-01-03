package com.jain.vidhyasagarsant.data.local.prefs;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jain.vidhyasagarsant.data.local.json.JsonParserHelper;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;
import com.jain.vidhyasagarsant.injection.ApplicationContext;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by @iamBedant on 31/05/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper  {

    public static final String PREF_FILE_NAME = "_pref_file";

    private final ObscuredSharedPreferences mPref;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {
        //mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mPref = ObscuredSharedPreferences.getPrefs(context, PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public String getString(String KEY, String DEFAULT) {
        return mPref.getString(KEY, DEFAULT);
    }

    public void putString(String KEY, String VALUE) {
        mPref.edit().putString(KEY, VALUE).apply();
    }

    public void putDouble(String KEY, Double VALUE) {
        mPref.edit().putLong(KEY, Double.doubleToRawLongBits(VALUE)).apply();
    }

    public Double getDouble(String KEY, Double DEFAULT) {
        return Double.longBitsToDouble(mPref.getLong(KEY, Double.doubleToLongBits(DEFAULT)));
    }

    public Boolean getBoolean(String KEY, Boolean DEFAULT) {
        return mPref.getBoolean(KEY, DEFAULT);
    }

    public void putBoolean(String KEY, Boolean VALUE) {
        mPref.edit().putBoolean(KEY, VALUE).apply();
    }

    @Override
    public Observable<?> getContent(Context myContext, String categoryType, String fileName){
        return getCategoryFromDisk();
    }

    @Override
    public Observable<Category> getCategoryFromDisk() {
        final String json = JsonParserHelper.getInstance().loadFromAsset("content.json");
        if(!TextUtils.isEmpty(json)){
            Gson gson = new Gson();
            Category categorydata = gson.fromJson(json, Category.class);
            return Observable.just(categorydata);
        }
        else {
            return Observable.fromCallable(new Callable<Category>() {
                @Override
                public Category call() throws Exception {
                    Gson gson = new Gson();
                    Category categorydata = null;
                    String categoryString = getString(AppConstants.PREF_CATEGORY, AppConstants.DEFAULT_CATEGORY);
                    if (!TextUtils.isEmpty(categoryString)) {
                        categorydata = gson.fromJson(categoryString, Category.class);
                    }

                    return categorydata;
                }
            });
        }
    }

    @Override
    public void saveCategoryToDisk(Category category) {
        Gson gson = new Gson();
        Timber.d("Saving Cache Data..");
        putString(AppConstants.PREF_CATEGORY,gson.toJson(category));
    }


    @Override
    public Observable<SubCategories> getSubCategoryFromDisk() {

        return Observable.fromCallable(new Callable<SubCategories>() {
            @Override
            public SubCategories call() throws Exception {
                Gson gson = new Gson();
                SubCategories subcategorydata = null;
                String subcategoryString = getString(AppConstants.PREF_SUBCATEGORY, AppConstants.DEFAULT_CATEGORY);
                if (!TextUtils.isEmpty(subcategoryString)) {
                    subcategorydata = gson.fromJson(subcategoryString, SubCategories.class);
                }

                return subcategorydata;
            }
        });



    }

    @Override
    public void saveSubCategoryToDisk(SubCategories subCategories) {
        Gson gson = new Gson();
        Timber.d("Saving Cache Data..");
        putString(AppConstants.PREF_SUBCATEGORY,gson.toJson(subCategories));
    }

    @Override
    public Observable<CategoryItemData> getSubCategoryItemFromDisk() {

        return Observable.fromCallable(new Callable<CategoryItemData>() {
            @Override
            public CategoryItemData call() throws Exception {
                Gson gson = new Gson();
                CategoryItemData subcategorydata = null;
                String subcategoryString = getString(AppConstants.PREF_SUBCATEGORY_ITEM, AppConstants.DEFAULT_CATEGORY);
                if (!TextUtils.isEmpty(subcategoryString)) {
                    subcategorydata = gson.fromJson(subcategoryString, CategoryItemData.class);
                }

                return subcategorydata;
            }
        });
    }

    @Override
    public void saveSubCategoryItemToDisk(CategoryItemData category) {
        Gson gson = new Gson();
        Timber.d("Saving Cache Data..");
        putString(AppConstants.PREF_SUBCATEGORY_ITEM,gson.toJson(category));
    }

}