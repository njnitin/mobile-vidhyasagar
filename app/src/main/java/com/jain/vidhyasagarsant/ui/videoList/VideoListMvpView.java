package com.jain.vidhyasagarsant.ui.videoList;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.MvpView;

import java.util.List;

/**
 * Created by kuliza-319 on 15/6/17.
 */

public interface VideoListMvpView extends MvpView {
    void setData(List<CategoryItemData.Response> response);
}
