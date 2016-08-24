package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Kitchen;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public interface KitchenDAO {

    void setDataSource(DataSource dataSources);

    List<Kitchen> getAllCookingDish();

    List<Kitchen> getAllReadyDish();

    boolean addAllDishFromOrder(int idOrder);

    boolean setDishReady(int orderId) throws SQLException;
}
