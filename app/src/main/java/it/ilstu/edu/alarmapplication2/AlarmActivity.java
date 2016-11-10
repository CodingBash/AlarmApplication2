package it.ilstu.edu.alarmapplication2;

/**
 * Created by Ryan on 11/8/2016.
 */

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.annotation.TargetApi;
import android.os.Build;

import java.util.Calendar;
import java.util.TimeZone;


public class AlarmActivity extends AppCompatActivity {

    TimePicker timePicker;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        EditText timeZone = (EditText) findViewById(R.id.timeZone);
        timeZone.setText(TimeZone.getDefault().toString());

        addButtonClickListener();


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SET_ALARM)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SET_ALARM)) {
            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SET_ALARM},
                        11);
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addButtonClickListener() {
        final Calendar calendar = Calendar.getInstance();
        Button setAlarmBtn = (Button) findViewById(R.id.set_alarm);
        setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                setAlarmActivity(calendar.getTimeInMillis()-20000);
            }
        });

        Button cancelAlarmBtn = (Button) findViewById(R.id.cancel_alarm);
        cancelAlarmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cancelAlarm();
            }

        });
    }

    public void setAlarmActivity(long time) {
        setAlarm(AlarmActivity.this, time, true);
    }

    public void setAlarm(Context context, long time, boolean isActive) {

        EditText userMsg = (EditText) findViewById(R.id.userMessage);
        String msg = userMsg.getText().toString();
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("msg", msg);
        intent.putExtra("time", "time");
        PendingIntent sender = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (isActive) {
            am.set(AlarmManager.RTC_WAKEUP, time, sender);
            Log.i("RYAN", "Alarm set to " + time);
            Log.i("RYAN", "System time in ms "+ System.currentTimeMillis());
        }
        else {
            am.cancel(sender);
            Log.i("RYAN", "Alarm Canceled for " + time);
        }

    }

    public void cancelAlarm(){
        long time = 0;
        setAlarm(this, time, false);
    }

    public EditText getUserMessage(){
        EditText msg = (EditText) findViewById(R.id.userMessage);
        return msg;
    }


}
