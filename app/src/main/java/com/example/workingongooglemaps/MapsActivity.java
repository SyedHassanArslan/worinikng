package com.example.workingongooglemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final double AYUBPARK_LAT = 33.5679557, AYUBPARK_LNG = 73.0806784;
    private static final float MAP_ZOOM = 15;
    private GoogleMap mMap;
    EditText edittext_location;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ().findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng ( AYUBPARK_LAT, AYUBPARK_LNG );
//        mMap.addMarker ( new MarkerOptions ().position ( sydney ).title ( "Marker in Sydney" ) );
//        mMap.moveCamera ( CameraUpdateFactory.newLatLng ( sydney ) );
//        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(AYUBPARK_LAT,AYUBPARK_LNG) , MAP_ZOOM) );

    }

    public void location_button(View view) throws IOException {

        hidekeyboard(view);

        edittext_location = findViewById ( R.id.edittext_location );
        String edittxt_location_string = edittext_location.getText ().toString ();
        if ( edittext_location.length () == 0 ){
            Toast.makeText ( this, "Enter Location", Toast.LENGTH_SHORT ).show ();
            return;
        }

        Geocoder geocoder = new Geocoder ( this );
        List<Address> list = geocoder.getFromLocationName ( edittxt_location_string,1 );
        Address address = list.get ( 0 );
        String locality = address.getLocality ();
//        Toast.makeText ( this, ""+locality, Toast.LENGTH_SHORT ).show ();

        double latitude = address.getLatitude ();
        double Longitude = address.getLongitude ();
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,Longitude) , MAP_ZOOM) );
        Toast.makeText ( this, ""+latitude, Toast.LENGTH_SHORT ).show ();
    }

    private void hidekeyboard(View view) {

        InputMethodManager methodManager = (InputMethodManager) getSystemService ( INPUT_METHOD_SERVICE );
        methodManager.hideSoftInputFromWindow ( view.getWindowToken (),0 );
    }

    @Override public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate ( R.menu.map_options, menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.mapTypeNone:
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.mapTypeSatellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeSateNormal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeSateHybird:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.mapTypeTerrian:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;

            default:
                return false;
        }
        return true;
    }

//    @Override
//    protected void onStop() {
//        super.onStop ();
//
//        // Saving map state before closing application
//        MapStateManager manager = new MapStateManager ( this );
//        manager.saveMapSate ( mMap );
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume ();
//
//        MapStateManager manager = new MapStateManager ( this );
//        CameraPosition position = manager.getSavedCameraPosition ();
//
//        if ( position != null){
//
//            CameraUpdate update = CameraUpdateFactory.newCameraPosition ( position );
//            mMap.moveCamera ( update );
//        }
//    }
}
