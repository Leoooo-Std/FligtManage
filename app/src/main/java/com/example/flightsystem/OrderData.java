package com.example.flightsystem;

public class OrderData {
    public int getOrderIdx() {
        return orderIdx;
    }

    public void setOrderIdx(int orderIdx) {
        this.orderIdx = orderIdx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    int orderIdx;
    String name;
    String time;
    String site;
    String service;

    public OrderData(int orderIdx,String name,String time,String site,String service){
        this.orderIdx = orderIdx;
        this.name = name;
        this.time = time;
        this.site = site;
        this.service = service;
    }
}
