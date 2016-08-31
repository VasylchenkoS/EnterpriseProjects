package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.OrderStateDAO;
import com.vasylchenko.jdbc.model.OrderState;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderStateController {

    OrderStateDAO orderStateDAO;

    public void setOrderStateDAO(OrderStateDAO orderStateDAO) {
        this.orderStateDAO = orderStateDAO;
    }

    public List<OrderState> getAllStates() {
        return orderStateDAO.getAllStates();
    }

    public OrderState getStateByName(String name) {
        return orderStateDAO.getStateByName(name);
    }
}
