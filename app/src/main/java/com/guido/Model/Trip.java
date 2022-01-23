package com.guido.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trip {
    private int id;
    private String name;
    private int admin_id;
    private Set<Location> locationSet;

    public Trip(int id, String name, int admin_id, Set<Location> locationList) {
        this.id = id;
        this.name = name;
        this.admin_id = admin_id;
        this.locationSet = new HashSet<>(locationList);
    }

    public Trip() {

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

    public Set<Location> getLocationSet() {
        return new HashSet<>(locationSet);
    }

    public void setLocationSet(Set<Location> locationSet) {
        this.locationSet = new HashSet<>(locationSet);
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin_id=" + admin_id +
                ", locationSet=" + locationSet +
                '}';
    }
}
