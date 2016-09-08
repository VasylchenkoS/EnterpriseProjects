package com.vasylchenko.java.model;

public class TodoItem {

    private String category;
    private boolean state;
    private String name;

    public TodoItem(String category, String name, boolean state) {
        this.category = category;
        this.state = state;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "category='" + category + '\'' +
                ", state=" + state +
                ", name='" + name + '\'' +
                '}';
    }
}
