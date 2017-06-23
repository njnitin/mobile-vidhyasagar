package com.jain.vidhyasagarsant.ui.CustomChrome;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by kuliza-303 on 05/06/17.
 */

public class KeepAliveService extends Service {
    private static final Binder sBinder = new Binder();


    @Override
    public IBinder onBind(Intent intent) {
        return sBinder;
    }
}
