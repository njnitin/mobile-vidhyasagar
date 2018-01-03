

package com.jain.vidhyasagarsant.ui.landing;

import android.content.Context;

import com.androidnetworking.error.ANError;
import com.jain.vidhyasagarsant.data.remote.model.Category;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import com.jain.vidhyasagarsant.R;
import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.ui.base.BasePresenter;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by @iamBedant on 15/03/17.
 */

public class LandingPresenter<V extends LandingMvpView> extends BasePresenter<V>
        implements LandingMvpPresenter<V> {

    private static final String TAG = "LandingPresenter";

    @Inject
    public LandingPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }


    @Override
    public void NavItemClicked(int nav_item) {
        switch (nav_item) {
            case R.id.nav_home:

                break;
            default:
                break;
        }
    }

    @Override
    public void getCategoryCollection() {
        getMvpView().showLoading();
        getDataManager()
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(new DisposableObserver<Category>() {
                    @Override
                    public void onNext(Category category) {
                        displayCategory(category);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError");
                        handleError(e);

                    }

                    @Override
                    public void onComplete() {
                        dispose();
                        Timber.d("onComplete");
                    }
                });
    }

    private void displayCategory(Category category) {
        getCompositeDisposable().add(Observable.just(category.getResponse())
                .flatMapIterable(new Function<List<Category.Response>, Iterable<Category.Response>>() {
                    @Override
                    public Iterable<Category.Response> apply(List<Category.Response> responses) throws Exception {
                        return responses;
                    }
                })
                .filter(new Predicate<Category.Response>() {
                    @Override
                    public boolean test(Category.Response category) {
                        return category.getIsDashboard() == true;
                    }
                })
                .toList()
//                .map(new Function<List<Category.Response>, List<Category.Response>>() {
//                    @Override
//                    public List<Category.Response> apply(List<Category.Response> responses) throws Exception {
//                        List<Category.Response> sortedlist = new ArrayList<>(responses.size());
//
//                        Collections.sort(responses, new Comparator<Category.Response>() {
//                            @Override
//                            public int compare(Category.Response o1, Category.Response o2) {
//                                return o2.getOrderNumber().compareTo(o2.getOrderNumber());
//                            }
//                        });
//
//                        return responses;
//                    }
//                })
                .subscribe(new Consumer<List<Category.Response>>() {
                    @Override
                    public void accept(List<Category.Response> responses) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().setData(responses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        handleError(throwable);
                    }
                }));

    }


    public void handleError(Throwable throwable) {
        if (!isViewAttached()) {
            return;
        }
        Timber.e(throwable);
        getMvpView().hideLoading();
        if (throwable instanceof ANError) {
            ANError anError = (ANError) throwable;
            handleApiError(anError);
        } else {
            getMvpView().showMessage(throwable.getLocalizedMessage());
        }
    }


    @Override
    public void dashboardClicked(Category.Response response) {

        if (response.getIsSubcategory()) {
            getMvpView().openSubcategoryScreen(response.getId() + "", response.getName());
        } else {

            switch (response.getType()) {
                case "News":
                    getMvpView().openNewsScreen(response.getId() + "");
                    break;
                case "content_list":
                    getMvpView().openContentList(response.getId() + "", response.getName());
                    break;
                case "gallery":
                    getMvpView().openGallery(response.getId() + "", response.getName());
                    break;
                case "video":
                    getMvpView().openVideoLibrary(response.getId() + "", response.getName());

                    break;
                default:
                    break;
            }
        }
    }


}
