
package com.jain.vidhyasagarsant.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by @iamBedant on 15/03/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}

