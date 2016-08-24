package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.dao.KitchenDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcKitchenDAO implements KitchenDAO {


    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcKitchenDAO.class);
    DataSource dataSource;

    @Autowired
    private JdbcOrderingDAO jdbcOrderingDAO;
    @Override
    public void setDataSource(DataSource dataSources) {
        this.dataSource = dataSources;
    }

    @Override
    public List<Kitchen> getAllCookingDish() {
        return getAllKitchen("Cooking");
    }

    public List<Kitchen> getAllKitchen(String value) {

        List<Kitchen> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "SELECT id_kitchen," +
                    "  employee.surname, \n" +
                    "  ordering.id_order, \n" +
                    "  kitchen.date, \n" +
                    "  kitchendishstate.state, \n" +
                    "  dish.name\n" +
                    "FROM \n" +
                    "  public.employee, \n" +
                    "  public.kitchendishstate, \n" +
                    "  public.dish, \n" +
                    "  public.orderdish, \n" +
                    "  public.ordering, \n" +
                    "  public.kitchen\n" +
                    "WHERE \n" +
                    "  kitchendishstate.id_state = kitchen.id_dishstate AND\n" +
                    "  dish.id_dish = orderdish.id_dish AND\n" +
                    "  orderdish.id_order = ordering.id_order AND\n" +
                    "  orderdish.id_dish = kitchen.id_dish AND\n" +
                    "  kitchen.id_employee = employee.id_employee AND\n" +
                    "  kitchen.id_order = ordering.id_order AND id_dishstate=" +
                    "(SELECT id_state FROM kitchendishstate WHERE kitchendishstate.state=?)")) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createKitchen(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }
    @Override
    public List<Kitchen> getAllReadyDish() {
        return getAllKitchen("Ready");
    }

    @Override
    public boolean addAllDishFromOrder(int idOrder) {
        for (String s : jdbcOrderingDAO.getDishInOrder(idOrder)) {
            addDish(idOrder, s);
        }
        return true;
    }

    private void addDish(int order, String dishName){
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO kitchen (id_employee, id_order, date, id_dish,id_dishstate)  VALUES " +
                            "(choose_cook(),?,now(),(SELECT dish.id_dish FROM dish WHERE dish.name=?),1)"
            )){
            statement.setInt(1, order);
            statement.setString(2, dishName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean setDishReady(int id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "UPDATE kitchen SET id_dishstate=2 " +
                    "WHERE id_kitchen=?"
            )) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error("cant update dish " + id);
            throw new RuntimeException(e);
        }
        return true;
    }

    private Kitchen createKitchen(ResultSet resultSet) throws SQLException {
        return new Kitchen(
                resultSet.getInt("id_kitchen"),
                resultSet.getString("surname"),
                resultSet.getInt("id_order"),
                resultSet.getString("state"),
                new Date(new java.util.Date().getTime()),
                resultSet.getString("name")
                );
    }
}
