package com.example.mylistview;

import android.widget.AlphabetIndexer;

/**
 * Created by joubin on 12/27/13.
 */
public class Car {
    private String make, condition;
    private int year, iconID;

    public Car(String make, String condition, int year, int iconID){
        super();
        this.make = make;
        this.year = year;
        this.condition = condition;
        this.iconID = iconID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
}
