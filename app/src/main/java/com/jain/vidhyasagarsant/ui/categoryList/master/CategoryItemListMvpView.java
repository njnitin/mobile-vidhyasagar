package com.jain.vidhyasagarsant.ui.categoryList.master;

import java.util.List;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.MvpView;

/**
 * Created by @iamBedant on 01/06/17.
 */

public interface CategoryItemListMvpView extends MvpView {
    void setData(List<CategoryItemData.Response> response);
}
