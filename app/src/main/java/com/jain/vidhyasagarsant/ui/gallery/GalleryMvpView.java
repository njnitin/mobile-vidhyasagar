package com.jain.vidhyasagarsant.ui.gallery;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.MvpView;

import java.util.List;

/**
 * Created by kuliza-303 on 07/06/17.
 */

public interface GalleryMvpView extends MvpView {
    void setData(List<CategoryItemData.Response> response);
}
