package com.jain.vidhyasagarsant.ui.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.iamBedant.starter.utils.GoogleAnalyticsManager;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jain.vidhyasagarsant.R.layout.activity_notification);
        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.NOTIFICATION_SCREEN_VISITED);
    }
}
