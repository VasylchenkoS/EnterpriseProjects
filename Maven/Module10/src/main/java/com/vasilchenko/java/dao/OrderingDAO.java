package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Ordering;

import java.util.List;

public interface OrderingDAO {

    void addNewOrder(Ordering order);

    void addDishesInOrder(Ordering order, List<Dish> dishName);

    void removeDishesFromOrder(Ordering order, List<Dish> dishName);

    void deleteOrder(Ordering order);

    void closeOrder(Ordering order);

    List<Ordering> showOpenOrder();

    List<Ordering> showClosedOrder();

    List<Ordering> getAllOrders();

    Ordering getOrderById(int id);
}