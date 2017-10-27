package com.imagecolletorsllc.imagecollectors;

/**
 * Created by nturner on 9/27/17.
 */

public class Convention {

    private String conventionName;
    private int startingClient;
    private int conventionYear;
    private int conventionMonth;
    private int conventionDay;

    public Convention(String name, int clientNum, int year, int month, int day){
        conventionName = name;
        startingClient = clientNum;
        conventionYear = year;
        conventionMonth = month;
        conventionDay = day;

    }

    public int getConventionYear() {
        return conventionYear;
    }

    public void setConventionYear(int conventionYear) {
        this.conventionYear = conventionYear;
    }

    public int getStartingClient() {
        return startingClient;
    }

    public void setStartingClient(int startingClient) {
        this.startingClient = startingClient;
    }

    public String getConventionName() {
        return conventionName;
    }

    public void setConventionName(String conventionName) {
        this.conventionName = conventionName;
    }

    public int getConventionMonth() {
        return conventionMonth;
    }

    public void setConventionMonth(int conventionMonth) {
        this.conventionMonth = conventionMonth;
    }

    public int getConventionDay() {
        return conventionDay;
    }

    public void setConventionDay(int conventionDay) {
        this.conventionDay = conventionDay;
    }
}
