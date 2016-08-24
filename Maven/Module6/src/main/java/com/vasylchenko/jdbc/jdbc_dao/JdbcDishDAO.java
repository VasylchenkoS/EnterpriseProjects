package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.dao.DishDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDishDAO implements DishDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDishDAO.class);

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSources) {
        this.dataSource = dataSources;
    }

    @Override
    @Transactional
    public List<Dish> getAllDish() {
        List<Dish> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT id_dish,name,category,price,weigth FROM dish " +
                    "INNER JOIN category ON dish.id_category = category.id_category";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(createDish(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Transactional
    @Override
    public List<Dish> findByName(String dishName) {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id_dish,name,category,price,weigth FROM dish " +
                    "INNER JOIN category ON dish.id_category = category.id_category " +
                             "WHERE name=?")){
            statement.setString(1,dishName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createDish(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean addNewDish(Dish dish) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO dish (name, id_category, price, weigth) VALUES " +
                             " (?,(SELECT id_category FROM category WHERE category=?),?,?)")) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getCategory());
            statement.setDouble(3, dish.getPrice());
            statement.setDouble(4, dish.getWeight());
            if (!statement.execute()) {
                LOGGER.info("Dish :\n" + dish.toString() + "\n successfully added");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteDishById(int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM dish WHERE id_dish = ?")) {
            Dish dish = getAllDish().stream().
                    filter(e -> e.getId()==dishId).findAny().orElse(null);
            statement.setInt(1, dishId);
            if (dish != null & !statement.execute()) {
                LOGGER.info("Dish :\n" + dish.toString() + "\n successfully deleted");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Dish> selectDish(String column, String value) {
        return null;
    }

    @Override
    public boolean checkDish(Dish dish) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM dish WHERE id_dish = ?")) {
            statement.setInt(1, dish.getId());
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Transactional
    @Override
    public ArrayList<String> getAllCategory() {
        ArrayList<String> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT category FROM category";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(resultSet.getString("category"));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    public Dish createDish(ResultSet resultSet) throws SQLException {
        return new Dish(
                resultSet.getInt("id_dish"),
                resultSet.getString("name"),
                resultSet.getString("category"),
                resultSet.getDouble("price"),
                resultSet.getDouble("weigth")
        );
    }
}
