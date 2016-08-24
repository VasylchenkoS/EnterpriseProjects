package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.dao.KitchenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public class KitchenController {

    @Autowired
    KitchenDAO kitchenDAO;

    @Transactional
    public List<Kitchen> getAllCookingDish() {
        return kitchenDAO.getAllCookingDish();
    }

    @Transactional
    public List<Kitchen> getAllReadyDish() {
        return kitchenDAO.getAllReadyDish();
    }

    @Transactional
    public boolean addAllDishFromOrder(int idOrder) {
        return kitchenDAO.addAllDishFromOrder(idOrder);
    }

    @Transactional
    public boolean setDishReady(int orderId) {
        try {
            return kitchenDAO.setDishReady(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
