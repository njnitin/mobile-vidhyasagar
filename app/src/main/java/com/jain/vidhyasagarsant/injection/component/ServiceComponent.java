
package com.jain.vidhyasagarsant.injection.component;

import com.jain.vidhyasagarsant.injection.PerService;
import com.jain.vidhyasagarsant.injection.module.ServiceModule;

import dagger.Component;

/**
 * Created by @iamBedant on 15/03/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


}
