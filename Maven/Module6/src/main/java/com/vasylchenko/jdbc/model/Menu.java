package com.vasylchenko.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int id;
    private String menuName;
    private List<String> dishList;

    public Menu() {
    }

    public Menu(int id, String menuName, List<String> dishList) {
        this.id = id;
        this.menuName = menuName;
        this.dishList = dishList;
    }

    public Menu(String menuName, List<String> dishList) {
        this.menuName = menuName;
        this.dishList = dishList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<String> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<String> dishList) {
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", dishList=" + dishList +
                '}';
    }
}
