package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.dao.DishDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DishController {

    private DishDAO dishDAO;

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Transactional
    public List<Dish> getAllDish() {
        return dishDAO.getAllDish();
    }


    @Transactional
    public List<Dish> getDishByName(String name) {
        return dishDAO.findByName(name);
    }

    @Transactional
    public boolean addNewDish(Dish dish){
        return dishDAO.addNewDish(dish);
    }

    @Transactional
    public boolean deleteDishById(int dishId){
        return dishDAO.deleteDishById(dishId);
    }

    @Transactional
    public List<Dish> selectDish(String column, String value){
        return dishDAO.selectDish(column,value);
    }

    @Transactional
    public boolean checkDish(Dish dish){
        return dishDAO.checkDish(dish);
    }

    @Transactional
    public List<String> getAllCategory() {
        return dishDAO.getAllCategory();
    }

}
