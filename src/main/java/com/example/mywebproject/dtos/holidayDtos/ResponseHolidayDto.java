package com.example.mywebproject.dtos.holidayDtos;

import com.example.mywebproject.dtos.locationDtos.ResponseLocationDto;

import java.util.Date;

public class ResponseHolidayDto {
    private long id;
    private ResponseLocationDto location;
    private String title;
    private Date date;
    private int duration;
    private double price;
    private int freeSlots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResponseLocationDto getLocation() {
        return location;
    }

    public void setLocation(ResponseLocationDto location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }
}
