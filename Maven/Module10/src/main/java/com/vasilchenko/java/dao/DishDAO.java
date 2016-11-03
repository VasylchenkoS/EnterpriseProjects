package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Dish;

import java.util.List;

public interface DishDAO {

    List<Dish> getAllDish();

    Dish findByName(String dishName);

    Dish findById(int id);

    void updateDish(Dish dish);

    void addNewDish(Dish dish);

    void deleteDishById(int dishId);

}