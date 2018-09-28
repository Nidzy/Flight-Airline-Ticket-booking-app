package com.novumlogic.flightapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.novumlogic.flightapp.Model.Destination;
import com.novumlogic.flightapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.novumlogic.flightapp.CommonUtilities.IMAGE_BASE_URL;

/**
 * Created by NOVUMLOGIC-2 on 5/22/2017.
 */

public class BookedFlightsAdapter extends RecyclerView.Adapter<BookedFlightsAdapter.CardViewHolder> {


    private String[] mDataSet;
    private ArrayList<Destination> mthali;
    private Context mContext;


    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    // Pass in the array into the constructor
    public BookedFlightsAdapter(Context mcontext, ArrayList<Destination> thalidetails) {

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
    public BookedFlightsAdapter.CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_airlines, viewGroup, false);
        return new BookedFlightsAdapter.CardViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(final BookedFlightsAdapter.CardViewHolder cardViewHolder, final int i) {

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

