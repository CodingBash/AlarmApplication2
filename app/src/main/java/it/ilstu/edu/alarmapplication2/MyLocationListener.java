package it.ilstu.edu.alarmapplication2;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by bbece on 11/9/2016.
 */
public class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            Log.i("BASH", "" + location.getLatitude());
            Log.i("BASH", "" + location.getLongitude());
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
