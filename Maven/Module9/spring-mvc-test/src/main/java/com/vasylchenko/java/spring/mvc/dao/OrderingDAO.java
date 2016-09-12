package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Dish;
import com.vasylchenko.java.spring.mvc.model.Ordering;

import java.util.List;

public interface OrderingDAO {

    void addNewOrder(Ordering order);

    void addDishesInOrder(Ordering order, List<Dish> dishName);

    void removeDishesFromOrder(Ordering order, List<Dish> dishName);

    void deleteOrder(Ordering order);

    void closeOrder(Ordering order);

    List<Ordering> showOpenOrder();

    List<Ordering> showClosedOrder();
}