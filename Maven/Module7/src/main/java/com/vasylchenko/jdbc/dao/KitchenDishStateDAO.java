package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.KitchenDishState;

import java.util.List;

public interface KitchenDishStateDAO {

    List<KitchenDishState> getAllKitchenDishStates();

    KitchenDishState getKitchenDishStateByName(String name);
}
