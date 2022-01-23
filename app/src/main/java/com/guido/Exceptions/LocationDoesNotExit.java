package com.guido.Exceptions;

public class LocationDoesNotExit extends Exception {
    public LocationDoesNotExit(int id) {
        super("No locations with the id of " + id);
    }
}
