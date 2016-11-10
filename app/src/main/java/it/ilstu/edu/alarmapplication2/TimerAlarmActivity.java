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
        setContentView(R.layout.timer_activity);
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
                int timeInMinutes = Integer.parseInt(minutesField.getText().toString());
                int timeInHours = Integer.parseInt(hoursField.getText().toString());
                setAlarmInActivity(timeInMinutes + (timeInHours*60));
            }
        });
    }

    public void setAlarmInActivity(int timeInMinutes) {
        setAlarm(this, timeInMinutes * 60 * 1000);
    }


    ///////////////////////////need to show the custom message notification when timer goes off
    public static void setAlarm(Context context, int time) {
        Long alertTime = System.currentTimeMillis() + time;
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add 30 seconds to the calendar object
        cal.add(Calendar.SECOND, alertTime.intValue());
        Intent intent = new Intent(context, TimerReceiver.class);
        intent.putExtra("time", time);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alertTime, sender);
        Log.i("BASH", "Alarm Set w/" + cal.getTimeInMillis());
    }
    // Chad's alarm just in case the one above doesn't work. Set's an alarm to go off at a user
    // specified minutes from now. All in Java, no alarmmanager
    /*public void minAlarm() throws Exception {
        System.out.println("Please Enter how many minutes from now"
                + " you would like the alarm to sound.");
        int go = myIn.nextInt();

        // Instantiates the time of two Calendar variables
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set both Calendar variables to current time
        cal.setTime(new Date());
        cal2.setTime(new Date());

        // Increment one Calendar variable ahead however many minutes
        // the user inputs
        System.out.println(cal.getTime());
        cal.add(Calendar.MINUTE, go);
        System.out.println(cal.getTime());


        // Checks current time until alarm time is reached
        while (cal2.before(cal)) {
            cal2.setTime(new Date());
        }

        // Sets off the alarm sound
        alarmNoise();
    }*/
}
