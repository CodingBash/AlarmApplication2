package it.ilstu.edu.alarmapplication2;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.icu.util.GregorianCalendar;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MovementActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private Criteria criteria;
    private String provider;
    private MyLocationListener locationListener;
    private static boolean locationChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        addButtonClickListener();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        11);
            }
        }
        locationListener = new MyLocationListener();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    private void addButtonClickListener() {
        Button setButton = (Button) findViewById(R.id.button);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText movementField = (EditText) findViewById(R.id.movementTime);
                EditText movementFieldSeconds = (EditText) findViewById(R.id.movementTimeSeconds);
                EditText movementCustomMessage = (EditText) findViewById(R.id.customMovementMessage);
                int timeInMinutes = Integer.parseInt(movementField.getText().toString());
                int timeInSeconds = Integer.parseInt(movementFieldSeconds.getText().toString());
                String customMessage = movementCustomMessage.getText().toString();
                setAlarmInActivity(((timeInMinutes*60) + timeInSeconds) * 1000, customMessage);

            }
        });
    }

    public void setAlarmInActivity(int time, String customMessage) {
        setAlarm(this, time, customMessage);
    }

    public static void setAlarm(Context context, int time, String customMessage) {
        Long alertTime = System.currentTimeMillis() + time;
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add 30 seconds to the calendar object
        cal.add(Calendar.SECOND, alertTime.intValue());
        Intent intent = new Intent(context, LocationAlertReciever.class);
        intent.putExtra("time", time);
        intent.putExtra("message", customMessage);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, alertTime, sender);
        Log.i("BASH", "Alarm Set w/ " + cal.getTimeInMillis());
    }

    public static void setLocationChange(boolean input){
        locationChange = input;
    }

    public static boolean getLocationChange(){
        return locationChange;
    }
}
