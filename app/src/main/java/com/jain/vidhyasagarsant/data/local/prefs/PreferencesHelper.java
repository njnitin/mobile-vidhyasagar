package com.jain.vidhyasagarsant.data.local.prefs;

import com.jain.vidhyasagarsant.data.remote.model.Category;

import io.reactivex.Observable;

/**
 * Created by @iamBedant on 31/05/17.
 */

public interface PreferencesHelper {

    Observable<Category> getCategoryFromDisk();

    void saveCategoryToDisk(Category category);
}