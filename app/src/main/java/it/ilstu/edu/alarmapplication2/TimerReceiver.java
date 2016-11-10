package it.ilstu.edu.alarmapplication2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Mike on 11/10/2016.
 */

public class TimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BASH", "RECIEVED");

        createNotification(context, intent.getStringExtra("message"));

    }

    public void createNotification(Context cxt, String customMessage) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(cxt);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);

        builder.setLargeIcon(BitmapFactory.decodeResource(cxt.getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Time's Up!");
        builder.setContentText(customMessage);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        NotificationManager notificationManager
                = (NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
        Log.i("BASH", "Notification sent");
    }


}
