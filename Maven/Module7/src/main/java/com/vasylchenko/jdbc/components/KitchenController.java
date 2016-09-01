package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.KitchenDAO;
import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.Ordering;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class KitchenController {

    KitchenDAO kitchenDAO;

    public void setKitchenDAO(KitchenDAO kitchenDAO) {
        this.kitchenDAO = kitchenDAO;
    }

    public List<Kitchen> getAllReadyDish() {
        return kitchenDAO.getAllReadyDish();
    }

    public List<Kitchen> getAllCookingDish() {
        return kitchenDAO.getAllCookingDish();
    }

    public void addAllDishFromOrder(Ordering order) {
        kitchenDAO.addAllDishFromOrder(order);
    }

    public void setDishReady(Kitchen kitchen) {
            kitchenDAO.setDishReady(kitchen);
    }
}
