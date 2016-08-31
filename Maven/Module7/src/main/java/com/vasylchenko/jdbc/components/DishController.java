package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.DishDAO;
import com.vasylchenko.jdbc.model.Dish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DishController {

    private DishDAO dishDAO;

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public List<Dish> getAllDish() {return dishDAO.getAllDish();}

    public Dish getDishByName(String name) {
        return dishDAO.findByName(name);
    }

    public void addNewDish(Dish dish) {
        dishDAO.addNewDish(dish);
    }

    public void deleteDishById(int dishId) {dishDAO.deleteDishById(dishId);}
}
