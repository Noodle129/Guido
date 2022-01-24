package com.guido.Model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Location implements Serializable {
    private int id;
    private String name;
    private String address;
    private double x;
    private double y;
    private int admin_id;
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
        this.admin_id = adminID;
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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", admin_id=" + admin_id +
                ", categorySet=" + categorySet +
                '}';
    }
}
