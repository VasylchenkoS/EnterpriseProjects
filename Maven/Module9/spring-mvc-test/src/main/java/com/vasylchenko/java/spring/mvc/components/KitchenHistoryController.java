package com.vasylchenko.java.spring.mvc.components;


import com.vasylchenko.java.spring.mvc.dao.KitchenHistoryDAO;
import com.vasylchenko.java.spring.mvc.model.Kitchen;
import com.vasylchenko.java.spring.mvc.model.KitchenHistory;

import java.util.List;

public class KitchenHistoryController {

    KitchenHistoryDAO kitchenHistoryDAO;

    public void setKitchenHistoryDAO(KitchenHistoryDAO kitchenHistoryDAO) {
        this.kitchenHistoryDAO = kitchenHistoryDAO;
    }

    public void saveReadyDish(Kitchen kitchen) {
        kitchenHistoryDAO.saveReadyDish(kitchen);
    }

    public List<KitchenHistory> getAllReadyDish() {
        return kitchenHistoryDAO.getAllReadyDish();
    }
}