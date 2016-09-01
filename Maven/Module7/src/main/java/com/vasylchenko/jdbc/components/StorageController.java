package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Storage;
import com.vasylchenko.jdbc.dao.StorageDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class StorageController {

    StorageDAO storageDAO;

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public List<Storage> getAllIngredient() {
        return storageDAO.getAllIngredient();
    }

    public void addNewIngredient(Storage ingredient) {
        storageDAO.addNewIngredient(ingredient);
    }

    public void deleteIngredientById(int ingredientId) {
        storageDAO.deleteIngredientById(ingredientId);
    }

    public void changeIngredientCount(Storage ingredient, long count) {
        storageDAO.changeIngredientCount(ingredient, count);
    }

    public Storage getIngredientByName(String ingredientName) {
        return storageDAO.getIngredientByName(ingredientName);
    }

    public List<Storage> checkIngredientCount(){return storageDAO.checkIngredientCount();}
}
