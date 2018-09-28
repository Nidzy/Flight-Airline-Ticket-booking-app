package com.novumlogic.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/26/2017.
 */

public class CheckinActivity extends AppCompatActivity {

    @BindView(R.id.imgclose)
    AppCompatImageView imgclose;

    @BindView(R.id.btnCheckin)
    Button btnCheckin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        //butterknife.
        ButterKnife.bind(this);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i_Choose = new Intent(getApplicationContext(), ChooseSeatsActivity.class);
                startActivity(i_Choose);

            }
        });
    }
}
