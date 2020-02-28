//package com.example.workingongooglemaps;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//
//public class MapStateManager {
//
//
//    private static final String LONGITUDE = "longitude";
//    private static final String LATITUDE = "Latitude";
//    private static final String ZOOM = "zoom";
//    private static final String BEARING = "bearing";
//    private static final String TILT = "tilt";
//    private static final String MAPTYPE = "MAPTYPE";
//
//    private SharedPreferences preferences;
//
////       Saving the State of Map Before Closing application
//    public MapStateManager(Context context){
//        preferences = context.getSharedPreferences ( "savestate",Context.MODE_PRIVATE );
//    }
////       Saving the State of Map Before Closing application
//    public void saveMapSate(GoogleMap map){
//
//        SharedPreferences.Editor editor = preferences.edit ();
//        CameraPosition position = map.getCameraPosition ();
//
//        editor.putFloat ( LATITUDE, (float) position.target.latitude );
//        editor.putFloat ( LONGITUDE, (float) position.target.longitude );
//        editor.putFloat ( ZOOM, position.zoom );
//        editor.putFloat ( BEARING, position.bearing );
//        editor.putFloat ( TILT, position.tilt );
//        editor.putInt ( MAPTYPE, map.getMapType () );
//        editor.commit ();
//    }
//
//    public CameraPosition getSavedCameraPosition(){
//        double latitude = preferences.getFloat ( LATITUDE,0 );
//        if ( latitude == 0 ){
//            return null;
//        }
//        double longitude = preferences.getFloat ( LONGITUDE,0 );
//        LatLng latLng = new LatLng ( latitude,longitude );
//        float zoom = preferences.getFloat ( ZOOM,0 );
//        float bearing = preferences.getFloat ( BEARING,0 );
//        float tilt = preferences.getFloat ( TILT,0 );
//
//        CameraPosition position = new CameraPosition ( latLng,zoom,tilt,bearing );
//        return position;
//    }
//}
//
