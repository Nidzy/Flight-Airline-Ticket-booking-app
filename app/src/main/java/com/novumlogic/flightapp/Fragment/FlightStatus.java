package com.novumlogic.flightapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.novumlogic.flightapp.ChooseSeatsActivity;
import com.novumlogic.flightapp.R;
;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/17/2017.
 */

public class FlightStatus extends Fragment {

    private Context mContext;

    @BindView(R.id.btnContinue)
    Button btnContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flightstatus, container, false);

        mContext = getActivity();

        ButterKnife.bind(this, rootView);

        initUi(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return rootView;
    }

    private void initUi(View view) {

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_choose = new Intent(mContext, ChooseSeatsActivity.class);
                startActivity(i_choose);
            }
        });
    }
}



