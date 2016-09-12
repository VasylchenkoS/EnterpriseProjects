package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Dish;

import java.util.List;

public interface DishDAO {

    List<Dish> getAllDish();

    Dish findByName(String dishName);

    void addNewDish(Dish dish);

    void deleteDishById(int dishId);
}