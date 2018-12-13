package com.example.acerpredator.androideatitserver.Model;

import java.util.List;

public class Request {
    private String name;
    private String note;
    private String total;
    private String status;
    private List<Order> foods;

    public Request() {
    }

    public Request(String name, String note, String total, List<Order> foods) {
        this.name = name;
        this.note = note;
        this.total = total;
        this.foods = foods;
        this.status = "0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
