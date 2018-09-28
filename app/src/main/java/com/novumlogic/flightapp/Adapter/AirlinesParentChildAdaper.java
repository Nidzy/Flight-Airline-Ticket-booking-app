package com.novumlogic.flightapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.novumlogic.flightapp.PassengersDetailsActivity;
import com.novumlogic.flightapp.R;

import java.util.List;

/**
 * Created by NOVUMLOGIC-2 on 6/1/2017.
 */

public class AirlinesParentChildAdaper extends ExpandableRecyclerAdapter<TitleParentViewHolder, TitleChildViewHolder> {

    LayoutInflater inflater;

    public AirlinesParentChildAdaper(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TitleParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.row_airlines, viewGroup, false);
        return new TitleParentViewHolder(view);
    }

    @Override
    public TitleChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.activity_childflightinformation, viewGroup, false);
        return new TitleChildViewHolder(view);
    }


    @Override
    public void onBindParentViewHolder(TitleParentViewHolder titleParentViewHolder, int i, Object o) {

    }

    @Override
    public void onBindChildViewHolder(TitleChildViewHolder titleChildViewHolder, final int i, Object o) {


        titleChildViewHolder.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_Passenger = new Intent(mContext, PassengersDetailsActivity.class);
                mContext.startActivity(i_Passenger);
            }
        });
    }
}
