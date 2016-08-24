package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Storage;
import com.vasylchenko.jdbc.model.dao.StorageDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StorageController {

    StorageDAO storageDAO;

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Transactional
    public List<Storage> getAllIngredient() {
        return storageDAO.getAllIngredient();
    }

    @Transactional
    public boolean addNewIngredient(Storage ingredient) {
        return storageDAO.addNewIngredient(ingredient);
    }

    @Transactional
    public boolean deleteIngredientById(int id) {
        return storageDAO.deleteIngredientById(id);
    }

    @Transactional
    public boolean changeIngredientCount(Storage ingredient) {
        return storageDAO.changeIngredientCount(ingredient);
    }

    @Transactional
    public List<Storage> findIngredientByName(String ingredientName) {
        return storageDAO.findIngredientByName(ingredientName);
    }

    @Transactional
    public List<Storage> checkIngredientCount() {
        return storageDAO.checkIngredientCount();
    }

    @Transactional
    public List<String> getAllIngredientName() {
        return storageDAO.getAllIngredientName();
    }
}
