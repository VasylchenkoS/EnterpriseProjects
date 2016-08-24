package com.vasylchenko.jdbc.model;

import java.sql.Date;
import java.util.List;

public class Ordering {
    private int id;
    private String waiterName;
    private String tableNumber;
    private Date date;
    private List<String> dishList;
    private String state;

    public Ordering(int id, String waiterName, String tableNumber, Date date, String state) {
        this.id = id;
        this.waiterName = waiterName;
        this.tableNumber = tableNumber;
        this.date = date;
        this.state = state;
    }

    public Ordering(String waiterName, String tableNumber, Date date, String state) {
        this.waiterName = waiterName;
        this.tableNumber = tableNumber;
        this.date = date;
        this.state = state;
    }

    public Ordering() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getDishList() {
        return dishList;
    }

    public void setDishList(List<String> dishList) {
        this.dishList = dishList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", waiterName='" + waiterName + '\'' +
                ", tableNumber='" + tableNumber + '\'' +
                ", date=" + date +
                ", dishList=" + dishList +
                ", state='" + state + '\'' +
                '}';
    }
}