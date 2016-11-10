package it.ilstu.edu.alarmapplication2;

/**
 * Created by Ryan on 11/8/2016.
 */

import android.content.Intent;
import android.content.Context;
//import android.view.View;
import android.widget.Button;
//import android.widget.TextView;
import android.widget.TimePicker;
//import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.AlarmManager;
//import android.widget.Button;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity
{
    private TimePicker alarmTimePicker;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

    }

    private void addButtonClickListener()
    {
        Button setAlarm = (Button) findViewById(R.id.set_alarm);

    }
    // Chads dateAlarm logic from last semester, may not need this of course but I figured it'd be
    // nice to have around just in case
   /* public void dateAlarm() throws Exception
    {

        Date date = new Date();
        String userDate = "";

        // Get user input to set alarm
        System.out.println(date);
        System.out.println("Please enter the date and time you would"
                + " like to set the alarm for in the format dd-MM-YYYY hh:mm (AM/PM).");
        userDate = myIn.nextLine();

        // Definies the format necessary for user input to Date class
        SimpleDateFormat alarmDate = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        String alarm = userDate;



        try
        {
            // Simply parses and prints out the alarm date, may remove later
            Date newDate = alarmDate.parse(alarm);
            System.out.println(newDate);

            // Checks if the alarm date/time is before the current time
            // then restarts the method if true
            if (newDate.before(date))
            {
                System.out.println("YOU CREATED A TIME PARADOX!");
                dateAlarm();
            }
            else
            {
                // Checks current time until alarm time is reached
                while (date.before(newDate))
                {
                    date = new Date();
                }
                // Plays Alarm Noise
                alarmNoise();
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }



    }*/


}
