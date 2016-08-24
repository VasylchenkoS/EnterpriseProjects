package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Dish;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public interface DishDAO {

    void setDataSource(DataSource dataSources);

    List<Dish> getAllDish();

    List<Dish> findByName(String dishName);

    boolean addNewDish(Dish dish);

    boolean deleteDishById(int dishId);

    List<Dish> selectDish(String column, String value);

    boolean checkDish(Dish dish);

    ArrayList<String> getAllCategory();
}
