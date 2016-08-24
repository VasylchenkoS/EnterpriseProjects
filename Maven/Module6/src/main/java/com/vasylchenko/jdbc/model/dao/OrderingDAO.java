package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Ordering;

import javax.sql.DataSource;
import java.util.List;

public interface OrderingDAO {

    void setDataSource(DataSource dataSources);

    boolean addNewOrder(Ordering order);

    boolean addDishToOrder(Ordering order, List<String> dishName);

    boolean removeDishFromOrder(Ordering order, List<String> dishName);

    boolean deleteOrderById(int Id);

    boolean closeOrderById(int Id);

    List<Ordering> showOpenOrder();

    List<Ordering> showClosedOrder();

    List<String> getDishInOrder(int id);
}