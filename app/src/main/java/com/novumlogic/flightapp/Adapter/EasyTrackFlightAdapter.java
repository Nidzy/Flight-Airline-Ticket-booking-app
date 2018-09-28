package com.novumlogic.flightapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novumlogic.flightapp.Model.Destination;
import com.novumlogic.flightapp.R;

import java.util.ArrayList;

/**
 * Created by NOVUMLOGIC-2 on 6/2/2017.
 */

public class EasyTrackFlightAdapter extends RecyclerView.Adapter<EasyTrackFlightAdapter.CardViewHolder> {


    private String[] mDataSet;
    private ArrayList<Destination> mthali;
    private Context mContext;


    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    // Pass in the array into the constructor
    public EasyTrackFlightAdapter(Context mcontext, ArrayList<Destination> thalidetails) {

        //super(mContext, R.layout.row_home,mvehicle);
        mthali = thalidetails;
        mContext = mcontext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private BookedFlightsAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(BookedFlightsAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @Override
    public EasyTrackFlightAdapter.CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_easytrackflight, viewGroup, false);
        return new EasyTrackFlightAdapter.CardViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final EasyTrackFlightAdapter.CardViewHolder cardViewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return mthali.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {


        public CardViewHolder(View v) {
            super(v);

        }
    }
}