package com.ndk.gapp;

import java.util.ArrayList;

public class Model_Location extends ArrayList {
    String id;
    String locname;
    String log;
    String lat;
    String status;

    public Model_Location() {
    }

    public Model_Location(String id, String locname, String loc, String logi, String status) {
        this.id=id;
        this.locname=locname;
        this.log=loc;
        this.lat=logi;
        this.status=status;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocname() {
        return locname;
    }

    public void setLocname(String locname) {
        this.locname = locname;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getLocname();

    }



}
