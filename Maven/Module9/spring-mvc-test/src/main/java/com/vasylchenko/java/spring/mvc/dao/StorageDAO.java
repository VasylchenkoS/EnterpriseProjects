package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Storage;

import java.util.List;

public interface StorageDAO {

    List<Storage> getAllIngredient();

    void addNewIngredient(Storage ingredient);

    void deleteIngredientById(int Id);

    void changeIngredientCount(Storage ingredient, long count);

    Storage getIngredientByName(String ingredientName);

    List<Storage> checkIngredientCount();
}
