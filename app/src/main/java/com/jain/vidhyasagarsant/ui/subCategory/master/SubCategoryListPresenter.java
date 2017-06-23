package com.jain.vidhyasagarsant.ui.subCategory.master;

import com.androidnetworking.error.ANError;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.Response;
import com.jain.vidhyasagarsant.data.remote.model.subcategories.SubCategories;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.ui.base.BasePresenter;

/**
 * Created by @iamBedant on 01/06/17.
 */

public class SubCategoryListPresenter<V extends SubCategoryListMvpView> extends BasePresenter<V>
        implements SubCategoryListMvpPresenter<V> {

    private static final String TAG = "LandingPresenter";

    @Inject
    public SubCategoryListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getCategoryData(String categoryId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getSubCategories(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SubCategories>() {
                    @Override
                    public void accept(SubCategories subCategories) throws Exception {
                        getMvpView().hideLoading();
                        if(subCategories.getError()==0){
                            getMvpView().setData(subCategories.getResponse());
                        }else {
                            getMvpView().showMessage(subCategories.getDetail());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }

    @Override
    public void itemClicked(Response mItem) {
        switch (mItem.getType()) {
            case "News":
                getMvpView().openNewsScreen(mItem.getId() + "");
                break;
            case "content_list":
                getMvpView().openContentList(mItem.getId() + "", mItem.getName());
                break;
            case "gallery":
                getMvpView().openGallery(mItem.getId() + "", mItem.getName());
                break;
            default:

                break;
        }
    }
}
