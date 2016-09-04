package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.KitchenHistory;

import java.util.List;

public interface KitchenHistoryDAO {

    void saveReadyDish(Kitchen kitchen);

    List<KitchenHistory> getAllReadyDish();
}
