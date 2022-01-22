package com.guido.Model;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private int id;
    private String name;
    private List<Location> locationList;

    public Trip(int id, String name, List<Location> locationList) {
        this.id = id;
        this.name = name;
        this.locationList = new ArrayList<>(locationList);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocationList() {
        return new ArrayList<>(locationList);
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = new ArrayList<>(locationList);
    }
}
