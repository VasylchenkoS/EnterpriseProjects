package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.OrderState;

import java.util.List;

public interface OrderStateDAO {

    List<OrderState> getAllStates();

    OrderState getStateByName(String name);
}
