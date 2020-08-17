package com.example.workassist.models;

import android.icu.util.ULocale;

public class Site {
    private int active=1;
    private String ownerName;
    private String location;
    private Float sqFeet ;
    private String remarks;
    public Site(){

    }
    public Site(String ownerName, String location, Float sqFeet, String remarks){
        this.ownerName=ownerName;
        this.location=location;
        this.sqFeet=sqFeet;
        this.remarks=remarks;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Float getSqFeet() {
        return sqFeet;
    }

    public String getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSqFeet(Float sqFeet) {
        this.sqFeet = sqFeet;
    }
}
