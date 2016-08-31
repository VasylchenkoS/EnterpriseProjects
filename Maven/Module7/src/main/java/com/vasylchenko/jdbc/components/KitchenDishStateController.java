package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.KitchenDishStateDAO;
import com.vasylchenko.jdbc.model.KitchenDishState;

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
