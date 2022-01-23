package com.guido.Model;


import java.util.HashSet;
import java.util.Set;

public class Location  {
    private int id;
    private String name;
    private String address;
    private double x;
    private double y;
    private int adminID;
    private Set<Category> categorySet;

    public Location(){
        this.categorySet = new HashSet<>();
    }

    public Location(int id, String name, String address, double x, double y, int adminID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
        this.adminID = adminID;
        this.categorySet = new HashSet<>();
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

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Set<Category> getCategorySet() {
        return new HashSet<>(categorySet);
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = new HashSet<>(categorySet);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
