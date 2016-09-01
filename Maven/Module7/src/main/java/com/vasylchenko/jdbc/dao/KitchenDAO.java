package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.Ordering;

import java.util.List;

public interface KitchenDAO {

    List<Kitchen> getAllCookingDish();

    List<Kitchen> getAllReadyDish();

    void addAllDishFromOrder(Ordering order);

    void setDishReady(Kitchen kitchen);
}
