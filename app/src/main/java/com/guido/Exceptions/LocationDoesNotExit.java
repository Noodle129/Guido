package com.guido.Exceptions;

public class LocationDoesNotExit extends Exception {
    public LocationDoesNotExit(int id) {
        super("No locations with an id of " + id);
    }
}
