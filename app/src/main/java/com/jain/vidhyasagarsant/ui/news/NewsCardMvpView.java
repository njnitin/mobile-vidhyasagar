package com.jain.vidhyasagarsant.ui.news;

import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.MvpView;

import java.util.List;

/**
 * Created by kuliza-303 on 31/05/17.
 */

public interface NewsCardMvpView extends MvpView {
    void setData(List<CategoryItemData.Response> response);
}
