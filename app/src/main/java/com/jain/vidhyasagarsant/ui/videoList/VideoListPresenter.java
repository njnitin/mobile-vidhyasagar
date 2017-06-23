package com.jain.vidhyasagarsant.ui.videoList;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.data.remote.model.categoryItems.CategoryItemData;
import com.jain.vidhyasagarsant.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kuliza-319 on 15/6/17.
 */

public class VideoListPresenter<V extends VideoListMvpView> extends BasePresenter<V> implements VideoListMvpPresenter<V> {

    @Inject
    public VideoListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getCategoryItemData(final String categoryId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getCategoryItemData(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryItemData>() {
                    @Override
                    public void accept(CategoryItemData categoryItemData) throws Exception {
                        getMvpView().hideLoading();
                        if (categoryItemData.getError() == 0) {
                            getMvpView().setData(categoryItemData.getResponse());
                        } else {
                            getMvpView().showMessage(categoryItemData.getDetail());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        if (!isViewAttached()) {
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
    public void getSubCategoryItemData(String categoryId, String subCategoryId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getSubCategoryItemData(categoryId,subCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryItemData>() {
                    @Override
                    public void accept(CategoryItemData categoryItemData) throws Exception {
                        getMvpView().hideLoading();
                        if (categoryItemData.getError() == 0) {
                            getMvpView().setData(categoryItemData.getResponse());
                        } else {
                            getMvpView().showMessage(categoryItemData.getDetail());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().hideLoading();
                        if (!isViewAttached()) {
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
}
