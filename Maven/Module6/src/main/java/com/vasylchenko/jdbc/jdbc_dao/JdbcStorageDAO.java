package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Storage;
import com.vasylchenko.jdbc.model.dao.StorageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStorageDAO implements StorageDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcMenuDAO.class);
    DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSources) {
        this.dataSource = dataSources;
    }

    @Override
    public List<Storage> getAllIngredient() {
        List<Storage> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM storage";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(createStorage(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    private Storage createStorage(ResultSet resultSet) throws SQLException {
        return new Storage(
                resultSet.getInt("id_ingridient"),
                resultSet.getString("ingredient"),
                resultSet.getLong("quantity"));
    }

    @Override
    public boolean addNewIngredient(Storage ingredient) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO storage (ingredient, quantity) VALUES (?,?)")){
            statement.setString(1, ingredient.getIngredientName());
            statement.setLong(2, ingredient.getQuantity());
            if (!statement.execute()) {
                LOGGER.info("Ingredient :\n" + ingredient.toString() + "\n successfully added");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteIngredientById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM storage WHERE id_ingridient = ?")) {
            Storage ingredient = getAllIngredient().stream().
                    filter(e -> e.getId()== id).findAny().orElse(null);
            statement.setInt(1, id);
            if (ingredient != null & !statement.execute()) {
                LOGGER.info("Ingredient :\n" + ingredient.toString() + "\n successfully deleted");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean changeIngredientCount(Storage ingredient) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE storage SET quantity=? WHERE ingredient=?")){
            statement.setLong(1, ingredient.getQuantity());
            statement.setString(2, ingredient.getIngredientName());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Storage> findIngredientByName(String ingredientName) {
        List<Storage> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM storage WHERE ingredient=?")){
            statement.setString(1, ingredientName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createStorage(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Storage> checkIngredientCount() {
        List<Storage> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM storage WHERE quantity < 50";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(createStorage(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<String> getAllIngredientName() {
        List<String> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT ingredient FROM storage";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(resultSet.getString("ingredient"));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }
}
