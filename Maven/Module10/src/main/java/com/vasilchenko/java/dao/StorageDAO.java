package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Storage;

import java.util.List;

public interface StorageDAO {

    List<Storage> getAllIngredient();

    void addNewIngredient(Storage ingredient);

    void deleteIngredientById(int Id);

    void updateIngredient(Storage ingredient);

    void changeIngredientCount(Storage ingredient, long count);

    Storage getIngredientByName(String ingredientName);

    Storage getIngredientById(int id);

    List<Storage> checkIngredientCount();
}
