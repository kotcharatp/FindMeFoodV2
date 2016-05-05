package com.egci392.findmefoodv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapLocation extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    double lat,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent i = getIntent();
        LatLng restaurant = new LatLng(i.getDoubleExtra("lat",0), i.getDoubleExtra("lng",0));
        mMap.addMarker(new MarkerOptions().position(restaurant).title(i.getStringExtra("name")));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurant, 18));
        mMap.setMyLocationEnabled(true);
/*
        //using LocationManager to get the latitude and longitude of current location
        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            lat = gps.getLatitude(); // returns latitude
            lng = gps.getLongitude(); // returns longitude
        }

        //line
        LatLng current = new LatLng(lat,lng);
        Polyline line = mMap.addPolyline(new PolylineOptions().add(current,restaurant).width(5).color(Color.GREEN));

        //find the distance between current location and restaurant
        Location locationA = new Location("point A");
        locationA.setLatitude(lat);
        locationA.setLongitude(lng);
        Location locationB = new Location("point B");
        locationB.setLatitude(i.getDoubleExtra("lat", 0));
        locationB.setLongitude(i.getDoubleExtra("lng",0));
        float distance = locationA.distanceTo(locationB);

        //show the distance in meters and kilometers
        Toast.makeText(MapLocation.this, String.format("%.2f km OR %.2f meters",distance/1000,distance), Toast.LENGTH_LONG).show();
    */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
