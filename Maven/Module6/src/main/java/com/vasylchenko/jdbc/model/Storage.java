package com.vasylchenko.jdbc.model;

public class Storage {
    private int id;
    private String ingredientName;
    private long quantity;

    public Storage(int id, String ingredientName, long quantity) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public Storage(String ingredientName, long quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}