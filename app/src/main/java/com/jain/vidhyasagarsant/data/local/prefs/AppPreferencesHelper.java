package com.jain.vidhyasagarsant.data.local.prefs;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.injection.ApplicationContext;
import com.jain.vidhyasagarsant.utils.AppConstants;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import timber.log.Timber;


@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

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
    public Observable<Category> getCategoryFromDisk() {

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

    @Override
    public void saveCategoryToDisk(Category category) {
        Gson gson = new Gson();
        Timber.d("Saving Cache Data..");
        putString(AppConstants.PREF_CATEGORY,gson.toJson(category));
    }


}