package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Kitchen;
import com.vasylchenko.java.spring.mvc.model.KitchenHistory;
import com.vasylchenko.java.spring.mvc.model.Ordering;

import java.util.List;

public interface KitchenDAO {

    List<Kitchen> getAllCookingDish();

    @SuppressWarnings("unchecked")
    List<KitchenHistory> getAllReadyDish();

    void addAllDishFromOrder(Ordering order);

    void setDishReady(Kitchen kitchen);
}
