package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Kitchen;
import com.vasilchenko.java.model.Ordering;

import java.util.List;

public interface KitchenDAO {

    List<Kitchen> getAllCookingDish();

    @SuppressWarnings("unchecked")
    List<? extends Kitchen> getAllReadyDish();

    void addAllDishFromOrder(Ordering order);

    void setDishReady(Kitchen kitchen);
}
