
package com.jain.vidhyasagarsant.injection.module;

import android.app.Application;
import android.content.Context;

import com.jain.vidhyasagarsant.BuildConfig;
import com.jain.vidhyasagarsant.data.local.prefs.PreferencesHelper;
import com.jain.vidhyasagarsant.data.remote.ApiHeader;
import com.jain.vidhyasagarsant.data.remote.ApiHelper;
import com.jain.vidhyasagarsant.utils.AppConstants;
import com.jain.vidhyasagarsant.data.AppDataManager;
import com.jain.vidhyasagarsant.data.DataManager;
import com.jain.vidhyasagarsant.data.local.prefs.AppPreferencesHelper;
import com.jain.vidhyasagarsant.data.remote.AppApiHelper;
import com.jain.vidhyasagarsant.injection.ApiInfo;
import com.jain.vidhyasagarsant.injection.ApplicationContext;
import com.jain.vidhyasagarsant.injection.DatabaseInfo;
import com.jain.vidhyasagarsant.injection.PreferenceInfo;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.PASSWORD;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                "",
                "");
    }

}
