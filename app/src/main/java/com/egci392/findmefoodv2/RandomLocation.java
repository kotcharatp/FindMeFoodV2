package com.egci392.findmefoodv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class RandomLocation extends AppCompatActivity {
    ResDataSource rds;
    Spinner locationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //DROP DOWN
        final Spinner areaSpinner = (Spinner)findViewById(R.id.chooseArea);
        locationSpinner = (Spinner)findViewById(R.id.chooseLocation);
        locationSpinner.setEnabled(false);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (areaSpinner.getSelectedItemPosition() == 0) {
                    ArrayAdapter<String> insideAdapter = new ArrayAdapter<String>(RandomLocation.this,
                            android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.InsideList));
                    locationSpinner.setAdapter(insideAdapter);
                } else {
                    ArrayAdapter<String> outsideAdapter = new ArrayAdapter<String>(RandomLocation.this,
                            android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.OutsideList));
                    locationSpinner.setAdapter(outsideAdapter);
                }
                locationSpinner.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //GO TO RESTAURANT INFO ACTIVITY
    public void gotoRestaurantInfoActivity(View v){
        rds = new ResDataSource(this);
        rds.open();
        List<res> r = rds.randomSelectedLocationFunction(locationSpinner.getSelectedItem().toString());

        //RANDOM THE RESTAURANT DATA INSIDE THE LIST
        Random random = new Random();
        int randomValue = random.nextInt(r.size());
        Intent i = new Intent(RandomLocation.this,RestaurantInfo.class);
        i.putExtra("name2", r.get(randomValue).getName());
        i.putExtra("info2", r.get(randomValue).getDesc());
        i.putExtra("lat2", r.get(randomValue).getLat());
        i.putExtra("lng2", r.get(randomValue).getLng());
        i.putExtra("picture2", r.get(randomValue).getPicID());
        i.putExtra("boolean2",false);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
