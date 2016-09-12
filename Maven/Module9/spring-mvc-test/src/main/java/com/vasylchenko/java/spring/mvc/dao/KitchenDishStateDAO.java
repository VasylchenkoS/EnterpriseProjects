package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.KitchenDishState;

import java.util.List;

public interface KitchenDishStateDAO {

    List<KitchenDishState> getAllKitchenDishStates();

    KitchenDishState getKitchenDishStateByName(String name);
}
