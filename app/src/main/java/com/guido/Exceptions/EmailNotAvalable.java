package com.guido.Exceptions;


public class EmailNotAvalable extends Exception{
    public EmailNotAvalable(String username) {
        super("The email " + username + " is already in use.");
    }
}