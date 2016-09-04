package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.KitchenHistoryDAO;
import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.KitchenHistory;

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