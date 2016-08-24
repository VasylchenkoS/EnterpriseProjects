package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Menu;
import com.vasylchenko.jdbc.model.dao.MenuDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMenuDAO implements MenuDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcMenuDAO.class);

    DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSources) {
        this.dataSource = dataSources;
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM menu";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(createMenu(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Menu> findByName(String menuName) {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM menu WHERE name=?")){
            statement.setString(1,menuName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createMenu(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean addNewMenu(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO menu (name) VALUES (?)")){
            statement.setString(1, menu.getMenuName());
            if (!statement.execute()) {
                LOGGER.info("Menu :\n" + menu.toString() + "\n successfully added");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteMenuById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM menu WHERE id_menu = ?")) {
            Menu menu = getAll().stream().
                    filter(e -> e.getId()== id).findAny().orElse(null);
            statement.setInt(1, id);
            if (menu != null & !statement.execute()) {
                LOGGER.info("Menu :\n" + menu.toString() + "\n successfully deleted");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Menu> selectMenu(String column, String value) {
        return null;
    }

    @Override
    public boolean checkMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean addDishIntoMenu(Menu menu, List<String> dishName) {
        try {
            for (String s : dishName) addDishToMenu(menu, s);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while update dishes in menu " + menu.getMenuName());
            return false;
        }
        return true;
    }

    public void addDishToMenu(Menu menu, String dishName)throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO menudish (id_menu, id_dish) VALUES " +
                            "(?,(SELECT dish.id_dish FROM dish WHERE dish.name=?))"
            )) {
            if (getAllDishFromMenu(menu).contains(dishName)) {
                LOGGER.error("Dish " + dishName + " already consist in menu " + menu.getMenuName());
                throw new RuntimeException();
            }
            statement.setInt(1, menu.getId());
            statement.setString(2, dishName);
            statement.execute();
        }
    }

    @Override
    public boolean deleteDishFromMenu(Menu menu, List<String> dishName) {
        try {
            for (String s : dishName) deleteDishMenu(menu, s);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while update dishes in menu " + menu.getMenuName());
            return false;
        }
        return true;
    }

    private void deleteDishMenu(Menu menu, String dishName)throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM menudish WHERE id_menu=? AND id_dish=(" +
                            "SELECT id_dish FROM dish WHERE name=?)"
            )) {
            statement.setInt(1, menu.getId());
            statement.setString(2, dishName);
            statement.execute();
        }
    }

    @Override
    public ArrayList<String> getAllDish() {
        ArrayList<String> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT dish.name FROM dish");
            while (resultSet.next()) result.add(resultSet.getString("name"));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    private Menu createMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        menu.setId(resultSet.getInt("id_menu"));
        menu.setMenuName(resultSet.getString("name"));
        menu.setDishList(getAllDishFromMenu(menu));
        return menu;
    }

    @Override
    public ArrayList<String> getAllDishFromMenu(Menu menuName) {
        ArrayList<String> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "SELECT dish.name FROM menudish\n" +
                        "INNER JOIN menu ON menudish.id_menu = menu.id_menu\n" +
                        "INNER JOIN dish ON menudish.id_dish = dish.id_dish\n" +
                        "WHERE menu.name=?"
            )) {
            statement.setString(1, menuName.getMenuName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(resultSet.getString("name"));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

}
