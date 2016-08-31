package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.OrderState;

import java.util.List;

public interface OrderStateDAO {

    List<OrderState> getAllStates();

    OrderState getStateByName(String name);
}
