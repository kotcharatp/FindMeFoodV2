package com.egci392.findmefoodv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    ResDataSource rds;
    ArrayAdapter<res> courseArrayAdapter;
    List<res> dList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar
        getSupportActionBar().setTitle("  Find Me Food");
        getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent value from searchForFood
        Intent i = getIntent();
        String location = i.getStringExtra("location");
        String nation = i.getStringExtra("nation");
        int max = i.getIntExtra("max", 0);
        int air = i.getIntExtra("air", 0);

        //retrieve data from database
        rds = new ResDataSource(this);
        rds.open();
        dList = rds.getSelectedres(location,nation,max,air);

        //show on listview
        final ListView mylist = (ListView)findViewById(R.id.mylist);
        courseArrayAdapter = new CourseArrayAdapter(this, 0,dList);
        mylist.setAdapter(courseArrayAdapter);

        //more detail on selected restaurant
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RestaurantList.this,RestaurantInfo.class);
                intent.putExtra("name",dList.get(position).getName());
                intent.putExtra("info",dList.get(position).getDesc());
                intent.putExtra("lat",dList.get(position).getLat());
                intent.putExtra("lng",dList.get(position).getLng());
                intent.putExtra("picture",dList.get(position).getPicID());
                intent.putExtra("boolean1",false);
                startActivity(intent);
            }
        });

    }

    //custom adapter
    class CourseArrayAdapter extends ArrayAdapter<res> {

        Context context;
        List<res> objects;
        public CourseArrayAdapter(Context context, int resource, List<res> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            res d = objects.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.data_list_layout, null);

            TextView txt = (TextView) view.findViewById(R.id.titleText);
            txt.setText(d.toString());
            ImageView image = (ImageView)view.findViewById(R.id.imageCourse);

            int pictureOfRestaurant = getResources().getIdentifier(d.getPicID(), "drawable", getPackageName());
            image.setImageResource(pictureOfRestaurant);
            return view;
        }
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
