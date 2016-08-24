package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Ordering;
import com.vasylchenko.jdbc.model.dao.OrderingDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderingController {

    private OrderingDAO orderingDAO;

    public void setOrderingDAO(OrderingDAO orderingDAO) {
        this.orderingDAO = orderingDAO;
    }

    @Transactional
    public boolean addNewOrder(Ordering order) {
        return orderingDAO.addNewOrder(order);
    }

    @Transactional
    public boolean addDishToOrder(Ordering order, List<String> dishName) {
        return orderingDAO.addDishToOrder(order, dishName);
    }

    @Transactional
    public boolean removeDishFromOrder(Ordering order, List<String> dishName) {
        return orderingDAO.removeDishFromOrder(order, dishName);
    }

    @Transactional
    public boolean deleteOrderById(int id) {
        return orderingDAO.deleteOrderById(id);
    }

    @Transactional
    public boolean closeOrderById(int id) {
        return orderingDAO.closeOrderById(id);
    }

    @Transactional
    public List<Ordering> showOpenOrder() {
        return orderingDAO.showOpenOrder();
    }

    @Transactional
    public List<Ordering> showClosedOrder() {
        return orderingDAO.showClosedOrder();
    }

    @Transactional
    public List<String> getDishInOrder(int id) {
        return orderingDAO.getDishInOrder(id);
    }
}
