package com.droiddigger.geofencingexample;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Created by mufad on 1/27/2017.
 */

public class GeofenceErrorMessages {

    public GeofenceErrorMessages() {
    }

    public static String getErrorString(Context context, int errorCode){
        Resources mResources = context.getResources();

        switch (errorCode){
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "Geofence service is not available now";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many geofences found";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intent found";
            default:
                return "Unknown error";
        }

    }

}
