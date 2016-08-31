package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Storage;

import java.util.List;

public interface StorageDAO {

    List<Storage> getAllIngredient();

    void addNewIngredient(Storage ingredient);

    void deleteIngredientById(int Id);

    void changeIngredientCount(Storage ingredient);

    Storage getIngredientByName(String ingredientName);

    List<Storage> checkIngredientCount();
}
