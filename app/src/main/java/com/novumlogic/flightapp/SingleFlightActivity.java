package com.novumlogic.flightapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.novumlogic.flightapp.Model.ChildItemAirline;
import com.novumlogic.flightapp.Model.ParentItemAirline;
import com.novumlogic.flightapp.Model.TitleCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/19/2017.
 */

public class SingleFlightActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.btnBookUber)
    Button btnBookUber;

    private GoogleMap mMap;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleflight);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Flight TK -721");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setElevation(0);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnBookUber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_pricestats = new Intent(getApplicationContext(), EasyTrackingActivity.class);
                startActivity(i_pricestats);

            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private List<ParentObject> initData() {
        TitleCreator titleCreator = TitleCreator.get(this);
        List<ParentItemAirline> titles = titleCreator.getAll();
        List<ParentObject> parentObject = new ArrayList<>();
        for (ParentItemAirline title : titles) {
            List<Object> childList = new ArrayList<>();
            childList.add(new ChildItemAirline("demo", "hiiii"));
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //for styling the google map.
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.style_json));
            if (!success) {
                // Handle map style load failure
            }
        } catch (Resources.NotFoundException e) {
            // Oops, looks like the map style resource couldn't be found!
        }

        Marker melbourne = mMap.addMarker(new MarkerOptions().position(sydney)
                .icon(getMarkerIcon("#68EADD")));
    }

    // method definition
    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }
}
