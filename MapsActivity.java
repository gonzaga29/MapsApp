package com.example.n3.mapsapp;

import android.location.Address;
import android.location.Geocoder;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private TextView txt;
    String end;
    private Marker mMarker;
    String end1;
    List<String> list;
    private String markerConst;
    LatLng sydney;
    private String end2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txt =  findViewById(R.id.txt_teste);
        end = "Recife - PE, Brasil";
        end1 = "Porto Alegre - SC, Brasil";
        end2 = "Salvador - BA, Brasil";
        list = new ArrayList<String>();
        list.add(end);
        list.add(end1);
        list.add(end2);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMarkerClickListener(this);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
         List<Address> addresses = null;
         //List<Address> addresses1 = null;

        try {

            int cont = 0;
            while (cont < list.size()) {

                addresses = geocoder.getFromLocationName(list.get(cont), 1);
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                sydney = new LatLng(latitude,longitude);
               // mMarker = new Marker();
                mMarker = mMap.addMarker(new MarkerOptions().position(sydney));
                mMarker.setTag(cont);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                cont++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        int cont1 = 0;
        markerConst = "m";
        while (cont1 < list.size()){

            if(marker.getId().equals(markerConst+cont1)){

               Toast.makeText(MapsActivity.this,list.get(cont1),Toast.LENGTH_SHORT).show();


            }
            cont1++;
    }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


    }

}
