package com.egci392.findmefoodv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RestaurantInfo extends AppCompatActivity {

    double lat,lng;
    String name,pic,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent values from restaurantList
        Intent i = getIntent();
        if(i.getBooleanExtra("boolean1",true) == false){
            name = i.getStringExtra("name");
            info = i.getStringExtra("info");
            lat = i.getDoubleExtra("lat",0);
            lng = i.getDoubleExtra("lng", 0);
            pic = i.getStringExtra("picture");
        }

        //GET INTENT VALUES FROM RANDOMLOCATION
        if(i.getBooleanExtra("boolean2",true) == false){
            name = i.getStringExtra("name2");
            info = i.getStringExtra("info2");
            lat = i.getDoubleExtra("lat2",0);
            lng = i.getDoubleExtra("lng2",0);
            pic = i.getStringExtra("picture2");
        }

        //GET INTENT VALUES FROM SHAKING ON THE MAINACTIVITY
        if(i.getBooleanExtra("boolean3",true) == false){
            name = i.getStringExtra("name3");
            info = i.getStringExtra("info3");
            lat = i.getDoubleExtra("lat3",0);
            lng = i.getDoubleExtra("lng3",0);
            pic = i.getStringExtra("picture3");
        }

        //show on screen
        TextView nameText = (TextView)findViewById(R.id.textLocation);
        TextView infotext = (TextView)findViewById(R.id.textInfo);
        ImageView image = (ImageView)findViewById(R.id.imageRes);
        nameText.setText(name);
        infotext.setText(info);
        int res = getResources().getIdentifier(pic, "drawable", getPackageName());
        image.setImageResource(res);
    }

    //PUSHING LOCATION BUTTON WILL GO TO MAPLOCATION ACTIVITY
    public void mapLocationFunction(View v){
        Intent intent = new Intent(RestaurantInfo.this,MapLocation.class);
        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        intent.putExtra("name",name);
        startActivity(intent);
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
