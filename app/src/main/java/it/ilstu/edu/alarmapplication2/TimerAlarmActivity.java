package it.ilstu.edu.alarmapplication2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;

/**
 * Created by Mike on 11/9/2016.
 */

public class TimerAlarmActivity  extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        addButtonClickListener();
    }

    private void addButtonClickListener()
    {

        Button resetHoursButton = (Button) findViewById(R.id.buttonResetHours);
        resetHoursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText hoursField = (EditText) findViewById(R.id.hourPicker);
                hoursField.setText("0");
            }
        });

        Button resetMinutesButton = (Button) findViewById(R.id.buttonResetMinutes);
        resetMinutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText minutesField = (EditText) findViewById(R.id.minutePicker);
                minutesField.setText("0");
            }
        });

        Button startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText minutesField = (EditText) findViewById(R.id.minutePicker);
                EditText hoursField = (EditText) findViewById(R.id.hourPicker);
                EditText notificationMessage = (EditText) findViewById(R.id.customMessage);
                int timeInMinutes = Integer.parseInt(minutesField.getText().toString());
                int timeInHours = Integer.parseInt(hoursField.getText().toString());
                String customMessage = notificationMessage.getText().toString();
                setAlarmInActivity(timeInMinutes + (timeInHours*60), customMessage);
            }
        });
    }

    public void setAlarmInActivity(int timeInMinutes, String customMessage) {
        setAlarm(this, timeInMinutes * 60 * 1000, customMessage);
    }


    ///////////////////////////need to show the custom message notification when timer goes off
    public static void setAlarm(Context context, int time, String customMessage) {
        Long alertTime = System.currentTimeMillis() + time;
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add 30 seconds to the calendar object
        cal.add(Calendar.SECOND, alertTime.intValue());
        Intent intent = new Intent(context, TimerReceiver.class);
        intent.putExtra("message", customMessage);
        PendingIntent sender = PendingIntent.getBroadcast(context, 12345, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alertTime, sender);
        Log.i("BASH", "Alarm Set w/" + cal.getTimeInMillis());
    }
}
