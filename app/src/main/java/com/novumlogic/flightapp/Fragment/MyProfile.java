package com.novumlogic.flightapp.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.novumlogic.flightapp.Adapter.BookFlightAdapter;
import com.novumlogic.flightapp.Adapter.CityGuidesAdapter;
import com.novumlogic.flightapp.Adapter.LongWeekendAdapter;
import com.novumlogic.flightapp.Adapter.PastBookingAdapter;
import com.novumlogic.flightapp.Adapter.UpcomingBookingAdapter;
import com.novumlogic.flightapp.Model.Destination;
import com.novumlogic.flightapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/17/2017.
 */

public class MyProfile extends Fragment {

    private Context mContext;

    private PastBookingAdapter mPastBookingAdapter;
    private ArrayList<Destination> mArrayListPastBooking;

    private UpcomingBookingAdapter mUpcomingAdapter;
    private ArrayList<Destination> mArraymUpBooking;

    @BindView(R.id.rvUpcomingBooking)
    RecyclerView rvUpcomingBooking;

    @BindView(R.id.pgbUpcomingBooking)
    ProgressBar pgbUpcomingBooking;

    @BindView(R.id.rvPastBooking)
    RecyclerView rvPastBooking;

    @BindView(R.id.pgbPastBooking)
    ProgressBar pgbPastBooking;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mybooking, container, false);

        mContext = getActivity();

        ButterKnife.bind(this, rootView);

        initUi(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return rootView;
    }

    private void initUi(View view) {

        //for better performance of recyclerview.
        rvPastBooking.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvPastBooking.setLayoutManager(llm);

        new LoadAllPastBooking().execute();


        //------------rv long weekends----------

        //for better performance of recyclerview.
        rvUpcomingBooking.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llml = new LinearLayoutManager(mContext);
        llml.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llml.setOrientation(LinearLayoutManager.VERTICAL);
        llml.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvUpcomingBooking.setLayoutManager(llml);

        new LoadAllUpcomingBooking().execute();

    }

    /*loading of upcoming booking*/

    Destination mDestination;

    class LoadAllUpcomingBooking extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbUpcomingBooking.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArraymUpBooking = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArraymUpBooking.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbUpcomingBooking.setVisibility(View.GONE);

            if (mArraymUpBooking != null && mArraymUpBooking.size() > 0) {
                mUpcomingAdapter = new UpcomingBookingAdapter(mContext, mArraymUpBooking);
                rvUpcomingBooking.setAdapter(mUpcomingAdapter);
                mUpcomingAdapter.notifyDataSetChanged();
            }
        }
    }

    /*loading of past booking*/

    class LoadAllPastBooking extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbPastBooking.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListPastBooking = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArrayListPastBooking.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbPastBooking.setVisibility(View.GONE);

            if (mArrayListPastBooking != null && mArrayListPastBooking.size() > 0) {
                mPastBookingAdapter = new PastBookingAdapter(mContext, mArrayListPastBooking);
                rvPastBooking.setAdapter(mPastBookingAdapter);
                mPastBookingAdapter.notifyDataSetChanged();
            }
        }
    }
}
