package com.novumlogic.flightapp.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.novumlogic.flightapp.R;

import butterknife.BindView;

/**
 * Created by NOVUMLOGIC-2 on 6/1/2017.
 */

public class TitleChildViewHolder extends ChildViewHolder {

    Button btnBooking;

    public TitleChildViewHolder(View itemView) {
        super(itemView);

        btnBooking = (Button) itemView.findViewById(R.id.btnBooking);

    }
}
