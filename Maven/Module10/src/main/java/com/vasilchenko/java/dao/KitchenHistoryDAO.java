package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Kitchen;
import com.vasilchenko.java.model.KitchenHistory;

import java.util.List;

public interface KitchenHistoryDAO {

    void saveReadyDish(Kitchen kitchen);

    List<KitchenHistory> getAllReadyDish();
}
