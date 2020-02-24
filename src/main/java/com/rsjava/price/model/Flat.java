package com.rsjava.price.model;

import org.springframework.stereotype.Component;

@Component
public class Flat {

    private String city;
    private int area;

    public Flat() {
    }

    public Flat(String city, int area) {
        this.city = city;
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
