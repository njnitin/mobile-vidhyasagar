package com.jain.vidhyasagarsant.firebase;

/**
 * Created by @iamBedant on 07/06/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jain.vidhyasagarsant.ui.news.NewsCardActivity;
import com.jain.vidhyasagarsant.ui.notifications.NotificationActivity;
import com.jain.vidhyasagarsant.utils.AppConstants;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String type = remoteMessage.getData().get("type");
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        String id = remoteMessage.getData().get("id");
        Intent intent;
        PendingIntent pendingIntent;
        switch (type) {
            case "news":

                intent = new Intent(this, NewsCardActivity.class);
                intent.putExtra(AppConstants.CATEGORY_ID,id);
                pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                sendNotification(title, message, pendingIntent);

                break;

            case "2":

                intent = new Intent(this, NotificationActivity.class);
                intent.putExtra("id", remoteMessage.getData().get("id"));
                pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                sendNotification(title, message, pendingIntent);
                break;

            default:
                intent = new Intent(this, NotificationActivity.class);
                intent.putExtra("id", remoteMessage.getData().get("id"));
                pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                sendNotification(title, message, pendingIntent);


        }


        Log.d(TAG, "Message data payload: " + remoteMessage.getData());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }


    private void sendNotification(String messageTitle, String messageBody, PendingIntent pendingIntent) {

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(com.jain.vidhyasagarsant.R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}