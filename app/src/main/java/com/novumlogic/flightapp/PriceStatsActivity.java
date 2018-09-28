package com.novumlogic.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/26/2017.
 */

public class PriceStatsActivity extends AppCompatActivity {

    @BindView(R.id.tvFlyback)
    TextView tvFlyback;

    @BindView(R.id.tvFlyout)
    TextView tvFlyout;

    @BindView(R.id.ivClose)
    AppCompatImageView ivClose;

    @BindView(R.id.btnContinueDates)
    Button btnContinueDates;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricestats);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);


        tvFlyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFlyback.setTextColor(getResources().getColor(R.color.white));
                tvFlyout.setTextColor(getResources().getColor(R.color.white20));
            }
        });

        tvFlyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFlyout.setTextColor(getResources().getColor(R.color.white));
                tvFlyback.setTextColor(getResources().getColor(R.color.white20));
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnContinueDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_Price = new Intent(getApplicationContext(), SearchFlightDetailActivity.class);
                startActivity(i_Price);
            }
        });
    }
}
