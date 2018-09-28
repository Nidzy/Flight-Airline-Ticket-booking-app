package com.novumlogic.flightapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.novumlogic.flightapp.Adapter.AirlinesParentChildAdaper;
import com.novumlogic.flightapp.Model.ChildItemAirline;
import com.novumlogic.flightapp.Model.ParentItemAirline;
import com.novumlogic.flightapp.Model.TitleCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/22/2017.
 */

public class AirlinesDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.rvAirlineslist)
    RecyclerView rvAirlineslist;

    @BindView(R.id.pgbAirlineslist)
    ProgressBar pgbAirlineslist;

    private GoogleMap mMap;

    private Context mContext;

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((AirlinesParentChildAdaper) rvAirlineslist.getAdapter()).onSaveInstanceState(outState);
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airlinesdetails);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        /* new using expandable recyclerview */
        rvAirlineslist.setLayoutManager(new LinearLayoutManager(this));
        AirlinesParentChildAdaper adapter = new AirlinesParentChildAdaper(this, initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        rvAirlineslist.setAdapter(adapter);

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