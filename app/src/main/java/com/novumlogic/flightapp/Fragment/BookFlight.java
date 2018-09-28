package com.novumlogic.flightapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.novumlogic.flightapp.Adapter.BookFlightAdapter;
import com.novumlogic.flightapp.Adapter.CityGuidesAdapter;
import com.novumlogic.flightapp.Adapter.DealsAdapter;
import com.novumlogic.flightapp.Adapter.LongWeekendAdapter;
import com.novumlogic.flightapp.Model.Destination;
import com.novumlogic.flightapp.R;
import com.novumlogic.flightapp.SearchFlightActivity;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by NOVUMLOGIC-2 on 5/17/2017.
 */

public class BookFlight extends Fragment {

    private BookFlightAdapter mBookFlightAdapter;
    private ArrayList<Destination> mArrayListDestination;

    private CityGuidesAdapter mCityGuideAdapter;
    private ArrayList<Destination> mArraymCityGuide;

    private DealsAdapter mDealstAdapter;
    private ArrayList<Destination> mArrayListDeals;

    private LongWeekendAdapter mLongWeekendAdapter;
    private ArrayList<Destination> mArrayListLongWeekend;

    private Context mContext;

    @BindView(R.id.rvPopulardestination)
    RecyclerView rvPopulardestination;

    @BindView(R.id.pgbPopulardestination)
    ProgressBar pgbPopulardestination;

    @BindView(R.id.rvlongWeekend)
    RecyclerView rvlongWeekend;

    @BindView(R.id.pgbLongWeekend)
    ProgressBar pgbLongWeekend;

    @BindView(R.id.rvDeals)
    RecyclerView rvDeals;

    @BindView(R.id.pgbDeals)
    ProgressBar pgbDeals;

    @BindView(R.id.rvCityGuides)
    RecyclerView rvCityGuides;

    @BindView(R.id.pgbCityGuide)
    ProgressBar pgbCityGuide;

    /*@BindView(R.id.etLocation)
    EditText etLocation;*/


    @BindView(R.id.acLocation)
    AutoCompleteTextView acLocation;

    private static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain", "New York", "London",
            "Dubai", "Amsterdam", "Prague", "Istanbul", "ischia", "isparta", "Ishmir", "Islamabad"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookflights, container, false);

        mContext = getActivity();

        ButterKnife.bind(this, rootView);


        initUi(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return rootView;
    }

    private void initUi(View view) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        acLocation.setAdapter(adapter);


        //------------rvpopular destination----------

        //for better performance of recyclerview.
        rvPopulardestination.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvPopulardestination.setLayoutManager(llm);

        new LoadAllPopularDestination().execute();


        //------------rv long weekends----------

        //for better performance of recyclerview.
        rvlongWeekend.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llml = new LinearLayoutManager(mContext);
        llml.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llml.setOrientation(LinearLayoutManager.HORIZONTAL);
        llml.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvlongWeekend.setLayoutManager(llml);

        new LoadAllLongWeekend().execute();


        //------------rv deals----------

        //for better performance of recyclerview.
        rvDeals.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmd = new LinearLayoutManager(mContext);
        llmd.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmd.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmd.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvDeals.setLayoutManager(llmd);

        new LoadAllDeals().execute();


        //------------rv cityguides----------

        //for better performance of recyclerview.
        rvCityGuides.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmc = new LinearLayoutManager(mContext);
        llmc.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmc.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmc.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvCityGuides.setLayoutManager(llmc);

        new LoadAllCityGuide().execute();


        /*etLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_searchflight = new Intent(getContext(), SearchFlightActivity.class);
                startActivity(i_searchflight);
            }
        });*/

    }

    /*loading of winter item*/

    Destination mDestination;

    class LoadAllPopularDestination extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbPopulardestination.setVisibility(View.VISIBLE);
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

            pgbPopulardestination.setVisibility(View.GONE);

            if (mArrayListDestination != null && mArrayListDestination.size() > 0) {
                mBookFlightAdapter = new BookFlightAdapter(mContext, mArrayListDestination);
                rvPopulardestination.setAdapter(mBookFlightAdapter);
                mBookFlightAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of long weekend item*/

    class LoadAllLongWeekend extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbLongWeekend.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListLongWeekend = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArrayListLongWeekend.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbLongWeekend.setVisibility(View.GONE);

            if (mArrayListLongWeekend != null && mArrayListLongWeekend.size() > 0) {
                mLongWeekendAdapter = new LongWeekendAdapter(mContext, mArrayListLongWeekend);
                rvlongWeekend.setAdapter(mLongWeekendAdapter);
                mLongWeekendAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of deals item*/

    class LoadAllDeals extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbDeals.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListDeals = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArrayListDeals.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbDeals.setVisibility(View.GONE);

            if (mArrayListDeals != null && mArrayListDeals.size() > 0) {
                mDealstAdapter = new DealsAdapter(mContext, mArrayListDeals);
                rvDeals.setAdapter(mDealstAdapter);
                mDealstAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of cityguide item*/

    class LoadAllCityGuide extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbCityGuide.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArraymCityGuide = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArraymCityGuide.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbCityGuide.setVisibility(View.GONE);

            if (mArraymCityGuide != null && mArraymCityGuide.size() > 0) {
                mCityGuideAdapter = new CityGuidesAdapter(mContext, mArraymCityGuide);
                rvCityGuides.setAdapter(mCityGuideAdapter);
                mCityGuideAdapter.notifyDataSetChanged();
            }
        }
    }
}
