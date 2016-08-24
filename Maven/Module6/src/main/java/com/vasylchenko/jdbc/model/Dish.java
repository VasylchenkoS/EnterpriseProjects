package com.vasylchenko.jdbc.model;

public class Dish {
    private int id;
    private String name;
    private String category;
    private double price;
    private double weight;

    public Dish(int id, String name, String category, double price, double weight) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
    }

    public Dish(String name, String category, double price, double weight) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
