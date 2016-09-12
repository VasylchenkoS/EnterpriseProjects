package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Dish;
import com.vasylchenko.java.spring.mvc.model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuDAO {

    List<Menu> getAllMenu();

    Menu findByName(String menuName);

    void addNewMenu(Menu menu);

    void deleteMenuById(int id);

    void updateDishesInMenu(Menu menu, Set<Dish> dishName);
}
