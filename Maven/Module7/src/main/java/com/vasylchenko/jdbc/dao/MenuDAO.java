package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuDAO {

    List<Menu> getAllMenu();

    Menu findByName(String menuName);

    void addNewMenu(Menu menu);

    void deleteMenuById(int id);

    void updateDishesInMenu(Menu menu, Set<Dish> dishName);
}
