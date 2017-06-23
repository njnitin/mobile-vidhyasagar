package com.jain.vidhyasagarsant;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.injection.component.ApplicationComponent;
import com.jain.vidhyasagarsant.injection.component.DaggerApplicationComponent;
import com.jain.vidhyasagarsant.injection.module.ApplicationModule;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by @iamBedant on 15/03/17.
 */

public class App extends Application {

    @Inject
    DataManager mDataManager;

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;


    private ApplicationComponent mApplicationComponent;
    private static App sInstance;

    public static App getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        sAnalytics = GoogleAnalytics.getInstance(this);
        sAnalytics.setLocalDispatchPeriod(5);
        sInstance = (App) getApplicationContext();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
            sTracker.enableExceptionReporting(true);
        }

        return sTracker;
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
