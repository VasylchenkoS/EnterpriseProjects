package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Storage;

import javax.sql.DataSource;
import java.util.List;

public interface StorageDAO {

    void setDataSource(DataSource dataSources);

    List<Storage> getAllIngredient();

    boolean addNewIngredient(Storage ingredient);

    boolean deleteIngredientById(int Id);

    boolean changeIngredientCount(Storage ingredient);

    List<Storage> findIngredientByName(String ingredientName);

    List<Storage> checkIngredientCount();

    List<String> getAllIngredientName();
}
