package com.guido.Model;

import java.io.Serializable;
import java.util.Set;

public class Model implements Serializable {
    private User u;
    private Set<Location> lcs;
    private Set<Trip> trips;

    public Model(User u, Set<Location> lcs, Set<Trip> trips) {
        this.u = u;
        this.lcs = lcs;
        this.trips = trips;
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

}
