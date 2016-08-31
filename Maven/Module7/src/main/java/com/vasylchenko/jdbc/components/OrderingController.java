package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.Ordering;
import com.vasylchenko.jdbc.dao.OrderingDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class OrderingController {

    private OrderingDAO orderingDAO;

    public void setOrderingDAO(OrderingDAO orderingDAO) {
        this.orderingDAO = orderingDAO;
    }

    public void addNewOrder(Ordering order) {
        orderingDAO.addNewOrder(order);
    }

    public void addDishesInOrder(Ordering order, List<Dish> dishName) {
        orderingDAO.addDishesInOrder(order, dishName);
    }

    public void removeDishesFromOrder(Ordering order, List<Dish> dishName) {
        orderingDAO.removeDishesFromOrder(order, dishName);
    }

    public void deleteOrder(Ordering order) {
        orderingDAO.deleteOrder(order);
    }

    public void closeOrder(Ordering order) {
        orderingDAO.closeOrder(order);
    }

    public List<Ordering> showOpenOrder() {
        return orderingDAO.showOpenOrder();
    }

    public List<Ordering> showClosedOrder() {
        return orderingDAO.showClosedOrder();
    }
}
