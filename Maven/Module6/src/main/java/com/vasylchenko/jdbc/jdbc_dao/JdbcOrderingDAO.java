package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Ordering;
import com.vasylchenko.jdbc.model.dao.OrderingDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderingDAO implements OrderingDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcOrderingDAO.class);

    DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSources) {
        this.dataSource = dataSources;
    }

    public List<Ordering> getAllOrder(String state) {
        List<Ordering> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "SELECT \n" +
                    "  ordering.id_order, \n" +
                    "  employee.surname, \n" +
                    "  tablesit.\"number\", \n" +
                    "  ordering.date, \n" +
                    "  orderstate.state\n" +
                    "FROM \n" +
                    "  public.ordering, \n" +
                    "  public.employee, \n" +
                    "  public.tablesit, \n" +
                    "  public.orderstate\n" +
                    "WHERE \n" +
                    "  ordering.id_employee = employee.id_employee AND\n" +
                    "  ordering.id_table = tablesit.id_table AND\n" +
                    "  orderstate.id_state = ordering.id_state AND\n" +
                    "  orderstate.state=?;")) {
            statement.setString(1, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createOrder(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean addNewOrder(Ordering order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO ordering(id_employee, id_table, date, id_state) VALUES " +
                             "((SELECT id_employee FROM employee WHERE surname=? AND " +
                             "id_position=(SELECT id_position FROM position WHERE position='Waiter'))," +
                             "(SELECT id_table FROM tablesit WHERE number=?)," +
                             "now(),1)")){
            statement.setString(1, order.getWaiterName());
            statement.setString(2, order.getTableNumber());
            if (!statement.execute()) {
                LOGGER.info("Order :\n" + order.toString() + "\n successfully added");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;

    }

    @Override
    public boolean addDishToOrder(Ordering order, List<String> dishName) {
        try {
            for (String s : dishName) insertDishIntoOrder(order, s);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while update dishes in menu " + order.getId());
            return false;
        }
        return true;
    }

    private void insertDishIntoOrder(Ordering order, String s) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO orderdish (id_order, id_dish) VALUES " +
                            "(?,(SELECT dish.id_dish FROM dish WHERE dish.name=?)" +
                            " AND check_aviable_ingridients(?)) "
            )) {
            if (getDishInOrder(order.getId()).contains(s) & !order.getState().equals("Open")) {
                LOGGER.error("Can't add dish to order");
                throw new RuntimeException();
            }
            statement.setInt(1, order.getId());
            statement.setString(2, s);
            statement.setString(3, s);
            statement.execute();
        }
    }

    @Override
    public boolean removeDishFromOrder(Ordering order, List<String> dishName) {
        if (order.getState().equals("Close")) {
            LOGGER.error("Order " + order.getId() + " already Closed");
            throw new RuntimeException();
        }
        try {
            for (String s : dishName) deleteDishFromOrder(order, s);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while update dishes in order " + order.getId());
            return false;
        }
        return true;
    }

    private void deleteDishFromOrder(Ordering order, String s) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM orderdish WHERE id_order=? AND id_dish=(" +
                            "SELECT id_dish FROM dish WHERE name=?)"
            )) {
            statement.setInt(1, order.getId());
            statement.setString(2, s);
            statement.execute();
        }
    }

    @Override
    public boolean deleteOrderById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM ordering WHERE id_order = ?")) {
            Ordering order = showOpenOrder().stream().
                    filter(e -> e.getId()== id).findAny().orElse(null);
            if (!order.getState().equals("Close")){
                statement.setInt(1, id);
                statement.execute();
                LOGGER.info("Order :\n" + order.toString() + "\n successfully deleted");
                return true;
            }
            else LOGGER.error("Order Closed. Can`t delete");
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;

    }

    @Override
    public boolean closeOrderById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE ordering SET id_state=2 WHERE id_order=?")){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return true;

    }

    @Override
    public List<Ordering> showOpenOrder() {
        return getAllOrder("Open");
    }

    @Override
    public List<Ordering> showClosedOrder() {
        return getAllOrder("Close");
    }

    @Override
    public List<String> getDishInOrder(int id) {
            ArrayList<String> result = new ArrayList<>();
            try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT dish.name FROM orderdish " +
                                "INNER JOIN ordering ON orderdish.id_order=ordering.id_order " +
                                "INNER JOIN dish ON orderdish.id_dish = dish.id_dish " +
                                "WHERE ordering.id_order=?"
                )) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) result.add(resultSet.getString("name"));
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to DB.");
                throw new RuntimeException(e);
            }
            return result;
        }

    private Ordering createOrder(ResultSet resultSet) throws SQLException {
        Ordering ordering = new Ordering(
                resultSet.getInt("id_order"),
                resultSet.getString("surname"),
                resultSet.getString("number"),
                resultSet.getDate("date"),
                resultSet.getString("state")
        );
        ordering.setDishList(getDishInOrder(ordering.getId()));
        return ordering;
    }
}
