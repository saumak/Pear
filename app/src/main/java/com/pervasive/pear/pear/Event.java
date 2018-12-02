package com.pervasive.pear.pear;

/**
 * Created by shubh on 12/2/2018.
 */

public class Event {

    private String Date;
    private String description;
    private String location;
    private String tittle;

    public Event(String date, String description, String location, String tittle) {
        Date = date;
        this.description = description;
        this.location = location;
        this.tittle = tittle;
    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTittle() {
        return tittle;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
