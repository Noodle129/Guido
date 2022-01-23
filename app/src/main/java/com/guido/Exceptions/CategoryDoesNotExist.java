package com.guido.Exceptions;

public class CategoryDoesNotExist extends Exception {
    public CategoryDoesNotExist(int id) {
        super("No categories with an id of " + id);
    }
}
