package com.droiddigger.geofencingexample;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by mufad on 1/28/2017.
 */
public final class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.droiddigger.geofencingexample";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 4000;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    //public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km
    public static final float GEOFENCE_RADIUS_IN_METERS = 1; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        BAY_AREA_LANDMARKS.put("HOME", new LatLng(23.759934, 90.361551));

        // Googleplex.
        BAY_AREA_LANDMARKS.put("Varsity", new LatLng(23.780271, 90.407261));

        // Test
        BAY_AREA_LANDMARKS.put("BijoySarani", new LatLng(23.764637,90.389186));
    }

}
