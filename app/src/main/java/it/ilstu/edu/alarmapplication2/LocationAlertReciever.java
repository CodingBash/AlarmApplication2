package it.ilstu.edu.alarmapplication2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by bbece on 11/9/2016.
 */

public class LocationAlertReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // Will display the notification in the notification bar
        Log.i("BASH", "Notification sent");

        Log.i("BASH", "RECIEVED");
            if (!MovementActivity.getLocationChange()) {
                Log.i("BASH", "Location didnt changed");
                createNotification(context, "Times up", "5 sec", "as");
            } else {
                Log.i("BASH", "Location  change");
                MovementActivity.setLocationChange(false);
                MovementActivity.setAlarm(context, intent.getIntExtra("time", 5000));
            }
    }

    public void createNotification(Context cxt, String msg, String msgText, String msgAlert) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(cxt);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);

        builder.setLargeIcon(BitmapFactory.decodeResource(cxt.getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Notifications Title");
        builder.setContentText("Notification Content Text");
        builder.setSubText("Notification sub text.");
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        NotificationManager notificationManager
                = (NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
        Log.i("BASH", "Notification sent");
    }
}
