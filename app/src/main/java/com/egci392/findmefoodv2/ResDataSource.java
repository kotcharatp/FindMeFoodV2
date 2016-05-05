package com.egci392.findmefoodv2;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kotcharat on 11/22/15.
 */
public class ResDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_DESC,
            DatabaseHelper.COLUMN_LOCATION,DatabaseHelper.COLUMN_NATION,  DatabaseHelper.COLUMN_MAXPRICE,
            DatabaseHelper.COLUMN_AIRCON, DatabaseHelper.COLUMN_LAT, DatabaseHelper.COLUMN_LON, DatabaseHelper.COLUMN_PICID };

    public ResDataSource(Context context) {dbHelper = new DatabaseHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //ADD RESTAURANTS TO THE DATABASE 2
    public void newres(String name, String desc, String location, String nation, int maxprice, int aircon,
                       double lat, double lng, String picid) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_DESC, desc);
        values.put(DatabaseHelper.COLUMN_LOCATION, location);
        values.put(DatabaseHelper.COLUMN_NATION, nation);
        values.put(DatabaseHelper.COLUMN_MAXPRICE, maxprice);
        values.put(DatabaseHelper.COLUMN_AIRCON, aircon);
        values.put(DatabaseHelper.COLUMN_LAT, lat);
        values.put(DatabaseHelper.COLUMN_LON, lng);
        values.put(DatabaseHelper.COLUMN_PICID, picid);

        long insertId = database.insert(DatabaseHelper.TABLE_RES, null, values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_RES, allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursorToUser(cursor);
        cursor.close();
    }

    //ADD RESTAURANTS TO THE DATABASE 1
    //THIS TECHNIQUE WILL CHECK THAT ARE THERE DATA IN DATABASE. IF THE DATA ALREADY IN THERE, WILL NOT DUPLICATE ADDING DATA
    public void addRestaurant() {

        Cursor cursor = database.rawQuery("SELECT * FROM res", null);
        if (cursor.getCount() < 22) {

            //social building
            newres("Kao Mun Kai HongTe", "Rice with Chicken", "Social Science Building",
                    "Thai", 40, 0, 13.792508, 100.324912, "s1");

            newres("Noi aunt", "Noodle wow so delicious", "Social Science Building",
                    "Thai", 40, 0, 13.792508, 100.324912, "s2");

            newres("Krua Pa jaew", "Food, Rice, Cheap, Delicious", "Social Science Building",
                    "Thai", 40, 0, 13.792508, 100.324912, "s18");

            newres("Duck noodle", "Noodle wow so delicious", "Social Science Building",
                    "Multination", 40, 0, 13.792508, 100.324912, "s19");

            //in front of MU
            newres("Glomglorm", "Delicious desert, lava cake, toast and many more", "In Front of MU",
                    "Thai", 80, 1, 13.793879, 100.328840, "s3");

            newres("Lodiham", "Delicious Steak dishes. Cheap food good quality", "In Front of MU",
                    "Multination", 80, 1, 13.795057, 100.327640, "s4");

            newres("Tangnomtho", "Delicious ice shave desert, cold and hot drinks", "In Front of MU",
                    "Thai", 80, 1, 13.795963, 100.327591, "s5");

            //ic building
            newres("Nub Ngern", "Food, Rice, Cheap, Delicious", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s6");

            newres("Neko Chef", "Japanese Food, Rice, Cheap, Delicious", "IC Building",
                    "Japanese", 50, 1, 13.792692, 100.325653, "s7");

            newres("IC Kao Mun Kai", "Rice with Chicken", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s8");

            newres("Ruai Tong", "Food, Rice, Cheap, Delicious", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s9");

            newres("For You Smoothy", "Juice So delicious", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s10");

            newres("Chicky Chic", "Fried chicken", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s11");

            newres("Snow Ice", "ice cream", "IC Building",
                    "Multination", 50, 1, 13.792692, 100.325653, "s12");

            newres("Inter Noodle", "ice cream", "IC Building",
                    "Thai", 50, 1, 13.792692, 100.325653, "s13");

            newres("Inthanin", "ice cream, juice, coco, lots of water, sandwitch", "IC Building",
                    "Thai", 100, 1, 13.792692, 100.325653, "s14");

            newres("Brew & Bev", "Coffee drinks, cold drinks, hot drinks, appetizers, main dishes and desserts", "IC Building",
                    "Multination", 100, 1, 13.792112, 100.325679, "s22");

            //circle
            newres("Mc Donald", "Fried chicken, Ham Burger, 24 hours", "Ratchapruk",
                    "Multination", 200, 1, 13.767014, 100.444300, "s15");

            newres("Sri Fah restaurant", "Delicious restaurent. Thai or Chinese food", "Ratchapruk",
                    "Multination", 200, 1, 13.767014, 100.444300, "s20");

            //central salaya
            newres("Zen", "japanese restaurant: many sushi, fish.", "Central Salaya",
                    "Japanese", 300, 1, 13.786748, 100.276325, "s16");

            newres("Barbq", "Barbq. pork, meat, chicken, shrimp, fish", "Central Salaya",
                    "Multination", 300, 1, 13.786748, 100.276325, "s21");

            //Lotus Salaya
            newres("Fuji", "japanese restaurant: many sushi, fish.", "Lotus Salaya",
                    "Japanese", 300, 1, 13.785575, 100.298003, "s17");
        }
        else{}
    }

    //RANDOM IN THE DATABASE
    public res randomFunction(){
        Cursor cursor =database.rawQuery("Select * From " + DatabaseHelper.TABLE_RES + " Order By RANDOM() LIMIT 1", null);
        cursor.moveToFirst();
        res myRes = cursorToUser(cursor);
        cursor.close();
        return myRes;
    }

    //SPECIFY THE LOCATION, GET DATA AND RANDOM IN THE LIST INSTEAD OF DATABASE
    public List<res> randomSelectedLocationFunction(String location){
        List<res> resList = new ArrayList<res>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_RES, allColumns, DatabaseHelper.COLUMN_LOCATION + " = '" +location+"'"
                ,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res restaurant = cursorToUser(cursor);
            resList.add(restaurant);
            cursor.moveToNext();
        }
        cursor.close();
        return resList;
    }

    private res cursorToUser(Cursor cursor) {
        res myRes = new res();
        myRes.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
        myRes.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)));
        myRes.setDesc(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESC)));
        myRes.setLocation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION)));
        myRes.setNation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NATION)));
        myRes.setMaxprice(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_MAXPRICE)));
        myRes.setAircon(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_AIRCON)));
        myRes.setLat(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_LAT)));
        myRes.setLng(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_LON)));
        myRes.setPicID(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PICID)));
        return myRes;
    }

    //GET ALL RESTAURANTS
    public List<res> getAllres() {
        List<res> resList = new ArrayList<res>();
        Cursor cursor = database.rawQuery("SELECT * FROM res", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res restaurant = cursorToUser(cursor);
            resList.add(restaurant);
            cursor.moveToNext();
        }
        cursor.close();
        return resList;
    }

    //GET SELECTED RESTAURANT
    //USER SPECIFY LOCATION, NATION, MAX PRICE AND AIRCONDITION
    public List<res> getSelectedres(String location, String nation, int max, int air){
        List<res> resList = new ArrayList<res>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_RES, allColumns, DatabaseHelper.COLUMN_LOCATION + " = '" +location+"'" + " and " +
                DatabaseHelper.COLUMN_NATION + " = '" +nation+"'" + " and " + DatabaseHelper.COLUMN_MAXPRICE + " < '" +max+"'"+ " and " +
                DatabaseHelper.COLUMN_AIRCON + " = '" +air+"'"
                ,null,null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res restaurant = cursorToUser(cursor);
            resList.add(restaurant);
            cursor.moveToNext();
        }
        cursor.close();
        return resList;
    }

}
