package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Kitchen;
import com.vasylchenko.java.spring.mvc.model.KitchenHistory;

import java.util.List;

public interface KitchenHistoryDAO {

    void saveReadyDish(Kitchen kitchen);

    List<KitchenHistory> getAllReadyDish();
}
