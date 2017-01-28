package com.droiddigger.geofencingexample;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mufad on 1/27/2017.
 */

public class GeofencetransitionIntentService extends IntentService{

    protected static final String TAG="gfservice";

    public GeofencetransitionIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            String errorMessage=GeofenceErrorMessages.getErrorString(this, geofencingEvent.getErrorCode());
            Log.d(TAG, errorMessage);
        }

        int geofenceTransition=geofencingEvent.getGeofenceTransition();
        if(geofenceTransition== Geofence.GEOFENCE_TRANSITION_ENTER|| geofenceTransition==Geofence.GEOFENCE_TRANSITION_EXIT){
            List<Geofence>triggeringGeofences=geofencingEvent.getTriggeringGeofences();
            String getGeofenceTriggeringDetails=getGeofenceTransitionDetails(this, geofenceTransition, triggeringGeofences);
            sendNotification(getGeofenceTriggeringDetails);
            Log.d(TAG, getGeofenceTriggeringDetails);
        }else {
            Log.d(TAG, "ERROR");
        }
    }

    private String getGeofenceTransitionDetails(Context context, int geofenceTransition, List<Geofence>triggeringGeofences){
        ArrayList triggeringGeofencesIdList=new ArrayList();
        for(Geofence geofence:triggeringGeofences){
            triggeringGeofencesIdList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString= TextUtils.join(", ", triggeringGeofencesIdList);
        return triggeringGeofencesIdsString;

    }

    public void sendNotification(String notificationDetails){
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent=stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_audiotrack).setLargeIcon(BitmapFactory.
                decodeResource(getResources(), R.drawable.ic_audiotrack)).
                setColor(Color.RED).setContentTitle(notificationDetails).
                setContentText("Click to open app").setContentIntent(notificationPendingIntent);
        builder.setAutoCancel(true);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
