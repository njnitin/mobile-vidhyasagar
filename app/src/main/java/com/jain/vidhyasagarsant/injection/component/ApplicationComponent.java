
package com.jain.vidhyasagarsant.injection.component;

import android.app.Application;
import android.content.Context;

import com.jain.vidhyasagarsant.injection.ApplicationContext;
import com.jain.vidhyasagarsant.injection.module.ApplicationModule;
import com.jain.vidhyasagarsant.App;
import com.jain.vidhyasagarsant.data.DataManager;

import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by @iamBedant on 15/03/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);


    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}