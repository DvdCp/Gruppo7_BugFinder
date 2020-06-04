package com.dcab.bugfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onMapReady(GoogleMap googleMap)
    {
        // Add a marker in Rome, Australia,
        // and move the map's camera to the same location.
        LatLng rome = new LatLng(41.981807, 12.0819104);
        LatLng ischia = new LatLng(40.953945, 15.624557);

        int height = 40;
        int width = 40;

        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bug_rilevation);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        LatLngBounds boundsItaly = new LatLngBounds(new LatLng(40.666397,10.172663), new LatLng(42.0914, 15.120292)); //SUD, OVEST - NORD, EST

        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        googleMap.addMarker(new MarkerOptions().position(rome).title("Marker in Rome").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        googleMap.addMarker(new MarkerOptions().position(ischia).title("Marker in Ischia").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(rome));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6),400, null);

        // Constrain the camera target to the Adelaide bounds.
        googleMap.setLatLngBoundsForCameraTarget(boundsItaly);
    }
}