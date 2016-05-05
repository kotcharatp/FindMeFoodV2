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
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchForFood extends AppCompatActivity {

    String fdLocation;
    String fdNation;
    int fdMaxprice=30;
    int fdAirCon=0;

    Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //drop down
        final Spinner areaSpinner = (Spinner)findViewById(R.id.chooseArea);
        locationSpinner = (Spinner)findViewById(R.id.chooseLocation);
        locationSpinner.setEnabled(false);

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (areaSpinner.getSelectedItemPosition() == 0) {
                    ArrayAdapter<String> insideAdapter = new ArrayAdapter<String>(SearchForFood.this,
                            android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.InsideList));
                    locationSpinner.setAdapter(insideAdapter);
                } else {
                    ArrayAdapter<String> outsideAdapter = new ArrayAdapter<String>(SearchForFood.this,
                            android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.OutsideList));
                    locationSpinner.setAdapter(outsideAdapter);
                }
                locationSpinner.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //price seek
        final SeekBar priceSeekbar = (SeekBar) findViewById(R.id.priceSeek);
        final TextView priceText = (TextView) findViewById(R.id.priceText);
        priceText.setText("30"); //Set initial default at minimum of 30

        priceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress + 30;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                priceText.setText(toString().valueOf(progressValue));
                fdMaxprice = progressValue;
            }
        });

        //check box
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) fdAirCon = 1;
                else fdAirCon = 0;
            }
        });
    }

    //PUSHING SEARCH BUTTON WILL GO TO RESTAURANTLIST ACTIVITY AND PASS DATA TO THAT PAGE
    public void SearchButtonFunction(View v){
        Spinner nationSpinner = (Spinner)findViewById(R.id.chooseNation);
        fdNation = nationSpinner.getSelectedItem().toString();
        fdLocation = locationSpinner.getSelectedItem().toString();
        Intent i = new Intent(SearchForFood.this,RestaurantList.class);
        i.putExtra("location",fdLocation);
        i.putExtra("nation",fdNation);
        i.putExtra("max",fdMaxprice);
        i.putExtra("air",fdAirCon);
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
