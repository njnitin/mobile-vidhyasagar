package com.jain.vidhyasagarsant.injection.module;

import android.app.Service;
import dagger.Module;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
