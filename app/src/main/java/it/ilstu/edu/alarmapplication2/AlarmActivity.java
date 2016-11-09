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
        addButtonClickListener();
        final Calendar calender = Calendar.getInstance();
        final Intent intent = new Intent(this.context, AlarmActivity.class);
    }

    private void addButtonClickListener()
    {
        Button setAlarm = (Button) findViewById(R.id.set_alarm);

    }




}
