package it.ilstu.edu.alarmapplication2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Ryan on 11/9/2016.
 */



public class AlarmReceiver extends BroadcastReceiver{
    String msgExtra;

    @Override
    public void onReceive(Context context, Intent intent) {

        //will display notification in the notification bar
        Log.i("RYAN", "Notification sent");

        Log.i("RYAN", "RECIEVED");


        Bundle extras = intent.getExtras();
        msgExtra = extras.getString("msg");

        Log.i("RYAN", "User Message:" + msgExtra);

        createNotification(context, "Alarm", msgExtra, "Alert");

    }

    private void createNotification(Context context, String alarm,
                                    String s, String alert) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentTitle(alarm);
        builder.setContentText(msgExtra);
        builder.setSubText("ALARM TRIGGERED");
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //display notificaion in the notification bar
        notificationManager.notify(1, builder.build());
        Log.i("RYAN", "Notification sent");
    }

}

