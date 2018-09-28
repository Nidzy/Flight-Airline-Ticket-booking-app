package com.novumlogic.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/19/2017.
 */

public class SearchFlightActivity extends AppCompatActivity {

    @BindView(R.id.btnSearchFlight)
    Button btnSearchFlight;

    @BindView(R.id.ivClose)
    AppCompatImageView ivClose;

    @BindView(R.id.llFlight)
    LinearLayout llFlight;

    @BindView(R.id.tvReturn)
    TextView tvReturn;

    @BindView(R.id.tvOneWay)
    TextView tvOneWay;

    @BindView(R.id.ivPlus)
    ImageView ivPlus;

    @BindView(R.id.ivPlus2)
    ImageView ivPlus2;

    @BindView(R.id.ivMinus)
    ImageView ivMinus;

    @BindView(R.id.ivMinus2)
    ImageView ivMinus2;

    @BindView(R.id.tvResult1)
    TextView tvResult1;

    @BindView(R.id.tvResult2)
    TextView tvResult2;

    @BindView(R.id.ivMorning)
    ImageView ivMorning;

    @BindView(R.id.ivNoon)
    ImageView ivNoon;

    @BindView(R.id.ivNight)
    ImageView ivNight;

    @BindView(R.id.ivEvening)
    ImageView ivEvening;

    int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchflight);

        ButterKnife.bind(this);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnSearchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_flightDetail = new Intent(getApplicationContext(), AddBaggageActivity.class);
                startActivity(i_flightDetail);
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                llFlight.setVisibility(View.VISIBLE);
                tvReturn.setTextColor(getResources().getColor(R.color.white));
                tvOneWay.setTextColor(getResources().getColor(R.color.dividerLight));

            }
        });

        tvOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llFlight.setVisibility(View.GONE);
                tvReturn.setTextColor(getResources().getColor(R.color.dividerLight));
                tvOneWay.setTextColor(getResources().getColor(R.color.white));
            }
        });

        ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                increaseCounter(view);
            }
        });

        ivPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                increaseCounter2(view);
            }
        });

        ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                decreaseCounter(view);
            }
        });

        ivMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCounter2(view);
            }
        });


        ivMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivMorning.setColorFilter(getResources().getColor(R.color.white));
                ivNight.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivNoon.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivEvening.setColorFilter(getResources().getColor(R.color.dividerLight));
            }
        });

        ivNoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivNoon.setColorFilter(getResources().getColor(R.color.white));
                ivEvening.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivNight.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivMorning.setColorFilter(getResources().getColor(R.color.dividerLight));
            }
        });

        ivEvening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivEvening.setColorFilter(getResources().getColor(R.color.white));
                ivNight.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivNoon.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivMorning.setColorFilter(getResources().getColor(R.color.dividerLight));
            }
        });

        ivNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ivNight.setColorFilter(getResources().getColor(R.color.white));
                ivMorning.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivNoon.setColorFilter(getResources().getColor(R.color.dividerLight));
                ivEvening.setColorFilter(getResources().getColor(R.color.dividerLight));
            }
        });


    }

    public void increaseCounter(View view) {
        counter = counter + 1;
        display(counter);

    }

    public void decreaseCounter(View view) {
        if (counter > 0) {
            counter -= 1;
        }
        display(counter);
    }

    private void display(int number) {
        tvResult1.setText("" + number);
    }


    public void increaseCounter2(View view) {
        counter = counter + 1;
        display2(counter);

    }

    public void decreaseCounter2(View view) {

        if (counter > 0) {
            counter -= 1;
        }
        display2(counter);
    }

    private void display2(int number2) {
        tvResult2.setText("" + number2);
    }
}
