package com.novumlogic.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/25/2017.
 */

public class ChooseSeatsActivity extends AppCompatActivity {


    @BindView(R.id.llSeat)
    LinearLayout llSeat;

    @BindView(R.id.imgFlight)
    ImageView imgFlight;

    @BindView(R.id.btnConfirmseats)
    Button btnConfirmseats;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseseats);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Choose your seats");

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setElevation(0);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        llSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imgFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    imgFlight.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imgFlight.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    imgFlight.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    imgFlight.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        btnConfirmseats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_TrackFlight = new Intent(getApplicationContext(), TrackFlightActivity.class);
                startActivity(i_TrackFlight);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
