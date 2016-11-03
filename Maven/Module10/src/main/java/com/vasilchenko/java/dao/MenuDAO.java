package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Menu;
import com.vasilchenko.java.model.Dish;

import java.util.List;
import java.util.Set;

public interface MenuDAO {

    List<Menu> getAllMenu();

    Menu findByName(String menuName);

    Menu getMenuById(int id);

    void addNewMenu(Menu menu);

    void deleteMenuById(int id);

    void updateMenu(Menu menu);

    void updateDishesInMenu(Menu menu, Set<Dish> dishName);

    List<Dish> getAllDishInMenu(int id);
}
