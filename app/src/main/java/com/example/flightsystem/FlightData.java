package com.example.flightsystem;

public class FlightData {
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String endTime;
    private String startCity;
    private String endCity;
    private String name;

    public FlightData(String startTime,String endTime,String startCity,String endCity,String name){
        this.startTime = startTime;
        this.endTime = endTime;
        this.startCity = startCity;
        this.endCity = endCity;
        this.name = name;
    }

}
