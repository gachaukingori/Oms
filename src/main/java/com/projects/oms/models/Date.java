/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.oms.models;

/**
 *
 */
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
@Component
public class Date {

    private int day;
    private int month;
    private int year;
    private String dateString;

    public Date() {
        this.dateString = "01.01.1900";
    }

    public Date(int day, int month, int year) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }

        if (month == 2 && day <= 28) {
            this.day = day;
        } else {
            this.day = day;
        }

        this.year = year;

        this.dateString = this.day + "." + this.month + "." + this.year;
    }

    public int getDay() { return day; }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public static SimpleDateFormat today() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public String toString() {
        return "Datum: " + "Tag: " + day + ", Monat: " + month + ", Jahr: " + year;
    }
}
