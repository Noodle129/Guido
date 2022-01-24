package com.guido.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private Set<Category> categories;


    public User(int id, String name, String email, String password, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.categories =  new HashSet<>(categories);
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        categories = null;
    }

    public User() {
        this.name = null;
        this.email = null;
        this.password = null;
        this.categories = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Category> getCategories() {
        return new HashSet<>(categories);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = new HashSet<>(categories);
        }
}


