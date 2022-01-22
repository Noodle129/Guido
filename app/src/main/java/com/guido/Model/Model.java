package com.guido.Model;

import java.util.Set;

public class Model {
    private User u;
    private Set<Location> lcs;
    private Set<Trip> trips;
    private double lat;
    private double lng;

    public Model(User u, Set<Location> lcs, Set<Trip> trips, double lat, double lng) {
        this.u = u;
        this.lcs = lcs;
        this.trips = trips;
        this.lat = lat;
        this.lng = lng;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Set<Location> getLcs() {
        return lcs;
    }

    public void setLcs(Set<Location> lcs) {
        this.lcs = lcs;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
