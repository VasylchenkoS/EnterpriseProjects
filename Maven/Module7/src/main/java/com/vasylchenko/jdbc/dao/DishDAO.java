package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Dish;

import java.util.List;

public interface DishDAO {

    List<Dish> getAllDish();

    Dish findByName(String dishName);

    void addNewDish(Dish dish);

    void deleteDishById(int dishId);
}