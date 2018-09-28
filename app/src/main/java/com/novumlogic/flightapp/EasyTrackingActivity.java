package com.novumlogic.flightapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

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
import com.novumlogic.flightapp.Adapter.BookFlightAdapter;
import com.novumlogic.flightapp.Adapter.EasyTrackFlightAdapter;
import com.novumlogic.flightapp.Model.Destination;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 6/2/2017.
 */

public class EasyTrackingActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.rvEasyTrack)
    RecyclerView rvEasyTrack;

    @BindView(R.id.pgbEasyTrack)
    ProgressBar pgbEasyTrack;

    private EasyTrackFlightAdapter mEasyTrackAdapter;
    private ArrayList<Destination> mArrayListDestination;

    private GoogleMap mMap;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easytracking);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ataturk Airport, Istanbul");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setElevation(0);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //------------rvEasyTrack destination----------

        //for better performance of recyclerview.
        rvEasyTrack.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvEasyTrack.setLayoutManager(llm);

        new LoadAllFlight().execute();

    }

     /*loading of winter item*/

    Destination mDestination;

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

    class LoadAllFlight extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbEasyTrack.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListDestination = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArrayListDestination.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbEasyTrack.setVisibility(View.GONE);

            if (mArrayListDestination != null && mArrayListDestination.size() > 0) {
                mEasyTrackAdapter = new EasyTrackFlightAdapter(mContext, mArrayListDestination);
                rvEasyTrack.setAdapter(mEasyTrackAdapter);
                mEasyTrackAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
