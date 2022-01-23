package com.guido.Exceptions;

public class TripDoesNotExist extends Exception{
    public TripDoesNotExist(int id) {
        super("No trips with an id of " + id);
    }
}
