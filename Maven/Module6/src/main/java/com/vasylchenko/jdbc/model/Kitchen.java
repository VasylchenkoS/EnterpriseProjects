package com.vasylchenko.jdbc.model;

import java.sql.Date;

public class Kitchen {
    private int id;
    private String cookName;
    private int orderNumber;
    private String status;
    private Date date;
    private String dishName;

    public Kitchen(int id, String cookName, int orderNumber, String status, Date date, String dishName) {
        this.id = id;
        this.cookName = cookName;
        this.orderNumber = orderNumber;
        this.status = status;
        this.date = date;
        this.dishName = dishName;
    }

    public Kitchen(String cookName, int orderNumber, String status, Date date, String dishName) {
        this.cookName = cookName;
        this.orderNumber = orderNumber;
        this.status = status;
        this.date = date;
        this.dishName = dishName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "id=" + id +
                ", cookName='" + cookName + '\'' +
                ", orderNumber=" + orderNumber +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", dishName='" + dishName + '\'' +
                '}';
    }
}
