package com.egci392.findmefoodv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kotcharat on 11/22/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_RES = "res";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "decs";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_NATION = "nation";
    public static final String COLUMN_MAXPRICE = "maxprice";
    public static final String COLUMN_AIRCON = "aircon";
    public static final String COLUMN_LAT = "lat" ;
    public static final String COLUMN_LON = "lng" ;
    public static final String COLUMN_PICID = "picid" ;

    private static final String DATABASE_NAME = "res.db";
    private static final int DATABASE_VERSION = 1;

    //private static final String DATABASE_CREATE = "create table users(_id integer primary key autoincrement, name text, password text, lat real, lng real);";

    private static final String DATABASE_CREATE = "create table " + TABLE_RES + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_DESC + " text, " +
            COLUMN_LOCATION + " text, " +
            COLUMN_NATION + " text, " +
            COLUMN_MAXPRICE + " integer, " +
            COLUMN_AIRCON  + " integer, " +
            COLUMN_LON + " real, " +
            COLUMN_LAT + " real, "+
            COLUMN_PICID + " text" + ");" ;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RES);
        onCreate(db);
    }

}
