package com.egci392.findmefoodv2;

/**
 * Created by kotcharat on 11/22/15.
 */
public class res {
    private long _id;
    private String name;
    private String desc;
    private String location;
    private String nation;
    private int maxprice;
    private int aircon;
    private double lat;
    private double lng;
    private String picID;

    public long getId() {return _id;}
    public void setId(long id) {this._id = id; }
    public String getName() {return name; }
    public void setName(String name) {this.name = name; }
    public void setDesc(String desc) {this.desc = desc; }
    public String getDesc() {return desc;}
    public void setLocation(String location) {this.location = location; }
    public String getLocation(){return location;}
    public void setNation(String nation) {this.nation = nation; }
    public void setMaxprice(int maxprice) {this.maxprice = maxprice; }
    public void setAircon(int aircon) {this.aircon = aircon; }
    public void setLat(double lat) {this.lat = lat; }
    public double getLat(){return lat;}
    public void setLng(double lng) {this.lng = lng; }
    public double getLng(){return lng;}
    public void setPicID(String picID) {this.picID = picID; }
    public String getPicID() { return picID; }

    @Override
    public String toString() {
        return name+ "\n" + desc;
    }

}
