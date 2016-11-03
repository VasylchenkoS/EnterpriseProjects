package com.vasilchenko.java.components;


import com.vasilchenko.java.dao.KitchenHistoryDAO;
import com.vasilchenko.java.model.Kitchen;
import com.vasilchenko.java.model.KitchenHistory;

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