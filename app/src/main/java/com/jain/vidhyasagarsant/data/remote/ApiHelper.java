
package com.jain.vidhyasagarsant.data.remote;

import android.content.Context;

import com.jain.vidhyasagarsant.data.remote.model.Category;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.data.remote.model.gallery.GalleryOutputModel;

import io.reactivex.Observable;

/**
 * Created by @iamBedant on 15/03/17.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<SubCategories> getSubCategories(String id);

    Observable<Category> getCategories();

    Observable<CategoryItemData> getCategoryItemData(String id);

    Observable<GalleryOutputModel> getGalleryImageList(String id);

    Observable getSubCategoryItemData(String categoryId, String subCategoryId);
}
