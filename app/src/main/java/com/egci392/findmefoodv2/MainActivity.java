package com.egci392.findmefoodv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private long lastUpdate;
    ResDataSource rds;
    res r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        
        //shake
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        //database
        rds = new ResDataSource(this);
        rds.open();
        rds.addRestaurant();
    }

    //ACTIVITY 1: search restaurants
    public void gotoSearchActivity(View v){
        startActivity(new Intent(MainActivity.this, SearchForFood.class));
    }

    //ACTIVITY 2: random by user select area
    public void gotoRandomfromLocation(View v){
        startActivity(new Intent(MainActivity.this, RandomLocation.class));
    }

    //ACTIVITY 3: shaking for get the restaurant
    @Override public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){ getAccelerometer(event); }
    }
    @Override public void onAccuracyChanged(Sensor sensor, int accuracy){}
    private void getAccelerometer(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float acclt = (x*x+ y*y+ z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if(acclt >=2) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            onPause();

            //get the random restaurant from database
            r = rds.randomFunction();
            //popup
            builder.setTitle("Congratulation!");
            builder.setMessage("We selected " + " '" + r.getName() + "' " +"for you");
            builder.setNegativeButton("Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, RestaurantInfo.class);
                    intent.putExtra("name3", r.getName());
                    intent.putExtra("info3", r.getDesc());
                    intent.putExtra("lat3", r.getLat());
                    intent.putExtra("lng3", r.getLng());
                    intent.putExtra("picture3", r.getPicID());
                    intent.putExtra("boolean3", false);
                    startActivity(intent);
                }
            });
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onResume();
                    dialog.cancel();
                }
            });
            builder.create();
            builder.show();
        }

    }

    @Override protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
