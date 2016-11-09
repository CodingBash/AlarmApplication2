package it.ilstu.edu.alarmapplication2;

/**
 * Created by Ryan on 11/8/2016.
 */

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity
{
    private TimePicker alarmTimePicker;
    private Context context;
    AlarmManager alarmManager;
    private AlarmReceiver alarm;
    private PendingIntent pending_intent;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        addButtonClickListener();

    }


    private void addButtonClickListener()
    {

        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        final Button setAlarm = (Button) findViewById(R.id.set_alarm);
        setAlarm.setOnClickListener(new View.OnClickListener()
        {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view)
            {
                final Calendar calendar = Calendar.getInstance();
                alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
                myIntent.putExtra("extra", "yes");
                pending_intent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                        myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
            }

        });

    }




}
