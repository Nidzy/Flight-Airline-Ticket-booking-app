package com.novumlogic.flightapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.novumlogic.flightapp.Adapter.AirlinesParentChildAdaper;
import com.novumlogic.flightapp.Adapter.BookFlightAdapter;
import com.novumlogic.flightapp.Adapter.BookedFlightsAdapter;
import com.novumlogic.flightapp.Adapter.ForYouAdapter;
import com.novumlogic.flightapp.Adapter.MyBookingParentChildAdapter;
import com.novumlogic.flightapp.Fragment.BookFlight;
import com.novumlogic.flightapp.Model.ChildItemAirline;
import com.novumlogic.flightapp.Model.Destination;
import com.novumlogic.flightapp.Model.MyBookingCreator;
import com.novumlogic.flightapp.Model.ParentItemAirline;
import com.novumlogic.flightapp.Model.TitleCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/22/2017.
 */

public class SingleBookingActivity extends AppCompatActivity {

    @BindView(R.id.rvBookedFlights)
    RecyclerView rvBookedFlights;

    @BindView(R.id.pgbBookedFlights)
    ProgressBar pgbBookedFlights;

    @BindView(R.id.rvForYou)
    RecyclerView rvForYou;

    @BindView(R.id.pgbForYou)
    ProgressBar pgbForYou;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbar_layout;

    private BookedFlightsAdapter mBookFlightAdapter;
    private ArrayList<Destination> mArrayListDestination;

    private ForYouAdapter mForYouAdapter;
    private ArrayList<Destination> mArrayListFotYouDestination;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlebooking);

        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar_layout.setTitle(Html.fromHtml("<font color='#ffffff'>Flight APP</font>"));
        toolbar_layout.setExpandedTitleColor(getResources().getColor(R.color.transperent));
        toolbar_layout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         /* new using expandable recyclerview */
        rvBookedFlights.setLayoutManager(new LinearLayoutManager(this));
        MyBookingParentChildAdapter adapter = new MyBookingParentChildAdapter(this, initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        rvBookedFlights.setAdapter(adapter);
        rvBookedFlights.stopNestedScroll();

         /*------------------------------------------*/
        //for better performance of recyclerview.
        rvForYou.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmf = new LinearLayoutManager(getApplicationContext());
        llmf.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmf.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmf.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvForYou.setLayoutManager(llmf);

        new LoadAllJustForyou().execute();

    }

    private List<ParentObject> initData() {
        MyBookingCreator titleCreator = MyBookingCreator.get(this);
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

      /*loading of winter item*/

    Destination mDestination;

    class LoadAllBookedFlights extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbBookedFlights.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListDestination = new ArrayList<Destination>();

                for (int i = 0; i < 3; i++) {
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

            pgbBookedFlights.setVisibility(View.GONE);

            if (mArrayListDestination != null && mArrayListDestination.size() > 0) {
                mBookFlightAdapter = new BookedFlightsAdapter(getApplicationContext(), mArrayListDestination);
                rvBookedFlights.setAdapter(mBookFlightAdapter);
                mBookFlightAdapter.notifyDataSetChanged();
            }
        }
    }


    class LoadAllJustForyou extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbForYou.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... args) {
            try {

                mArrayListFotYouDestination = new ArrayList<Destination>();

                for (int i = 0; i < 5; i++) {
                    mDestination = new Destination();
                    mDestination.setId(i);
                    mDestination.setName("Demo");
                    mArrayListFotYouDestination.add(mDestination);
                    mDestination = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            pgbForYou.setVisibility(View.GONE);

            if (mArrayListFotYouDestination != null && mArrayListFotYouDestination.size() > 0) {
                mForYouAdapter = new ForYouAdapter(getApplicationContext(), mArrayListFotYouDestination);
                rvForYou.setAdapter(mForYouAdapter);
                mForYouAdapter.notifyDataSetChanged();
            }
        }
    }
}


