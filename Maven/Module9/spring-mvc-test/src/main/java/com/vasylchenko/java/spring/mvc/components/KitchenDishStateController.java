package com.vasylchenko.java.spring.mvc.components;

import com.vasylchenko.java.spring.mvc.dao.KitchenDishStateDAO;
import com.vasylchenko.java.spring.mvc.model.KitchenDishState;

import java.util.List;

public class KitchenDishStateController {

    KitchenDishStateDAO kitchenDishStateDAO;

    public void setKitchenDishStateDAO(KitchenDishStateDAO kitchenDishStateDAO) {
        this.kitchenDishStateDAO = kitchenDishStateDAO;
    }

    public List<KitchenDishState> getAllKitchenDishStates() {
        return kitchenDishStateDAO.getAllKitchenDishStates();
    }

    public KitchenDishState getKitchenDishStateByName(String name) {
        return kitchenDishStateDAO.getKitchenDishStateByName(name);
    }
}
