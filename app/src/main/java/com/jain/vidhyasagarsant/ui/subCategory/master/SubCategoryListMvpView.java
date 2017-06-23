package com.jain.vidhyasagarsant.ui.subCategory.master;

import com.jain.vidhyasagarsant.data.remote.model.subcategories.Response;

import java.util.List;

import com.jain.vidhyasagarsant.ui.base.MvpView;

/**
 * Created by @iamBedant on 01/06/17.
 */

public interface SubCategoryListMvpView extends MvpView {
    void setData(List<Response> response);

    void openNewsScreen(String s);

    void openContentList(String s, String name);

    void openGallery(String s, String name);
}
