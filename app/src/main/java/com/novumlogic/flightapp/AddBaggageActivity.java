package com.novumlogic.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NOVUMLOGIC-2 on 5/19/2017.
 */

public class AddBaggageActivity extends AppCompatActivity {

    @BindView(R.id.btnAddBaggage)
    Button btnAddBaggage;

    @BindView(R.id.spBag)
    Spinner spBag;

    @BindView(R.id.spBag2)
    Spinner spBag2;

    @BindView(R.id.spKg)
    Spinner spKg;

    @BindView(R.id.spKg2)
    Spinner spKg2;

    @BindView(R.id.spMeal)
    Spinner spMeal;

    @BindView(R.id.spmeal2)
    Spinner spmeal2;

    @BindView(R.id.btnSeats)
    Button btnSeats;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbaggage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add baggage");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
        getSupportActionBar().setElevation(0);

        ButterKnife.bind(this);

        btnAddBaggage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrackFlightActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.bags, R.layout.spinner_item);
        spBag.setAdapter(adapter);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                R.array.bags, R.layout.spinner_item);
        spBag2.setAdapter(adapter2);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,
                R.array.kgs, R.layout.spinner_item);
        spKg.setAdapter(adapter3);

        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this,
                R.array.kgs, R.layout.spinner_item);
        spKg2.setAdapter(adapter4);

        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this,
                R.array.meals, R.layout.spinner_item);
        spmeal2.setAdapter(adapter5);

        ArrayAdapter adapter6 = ArrayAdapter.createFromResource(this,
                R.array.low_fatmeals, R.layout.spinner_item);
        spMeal.setAdapter(adapter6);

        btnSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PassengersDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
