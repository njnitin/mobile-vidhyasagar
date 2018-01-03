package com.jain.vidhyasagarsant.data.local.prefs;

import android.content.Context;

import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;

import io.reactivex.Observable;

/**
 * Created by @iamBedant on 31/05/17.
 */

public interface PreferencesHelper {

    Observable<?> getContent(Context myContext, String categoryType, String fileName);

    Observable<Category> getCategoryFromDisk();

    void saveCategoryToDisk(Category category);


    Observable<SubCategories> getSubCategoryFromDisk();

    void saveSubCategoryToDisk(SubCategories category);

    Observable<CategoryItemData> getSubCategoryItemFromDisk();

    void saveSubCategoryItemToDisk(CategoryItemData category);
}