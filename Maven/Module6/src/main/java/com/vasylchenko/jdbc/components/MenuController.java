package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Menu;
import com.vasylchenko.jdbc.model.dao.MenuDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    MenuDAO menuDAO;

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Transactional
    public List<Menu> getAll() {
        return menuDAO.getAll();
    }

    @Transactional
    public List<Menu> findByName(String menuName) {
        return menuDAO.findByName(menuName);
    }

    @Transactional
    public boolean addNewMenu(Menu menu) {
        return menuDAO.addNewMenu(menu);
    }

    @Transactional
    public boolean deleteMenuById(int id) {
        return menuDAO.deleteMenuById(id);
    }

    @Transactional
    public List<Menu> selectMenu(String column, String value) {
        return null;
    }

    @Transactional
    public boolean checkMenu(Menu menu) {
        return menuDAO.checkMenu(menu);
    }

    @Transactional
    public boolean addDishIntoMenu(Menu menu, List<String> dishName) {
        return menuDAO.addDishIntoMenu(menu, dishName);
    }

    @Transactional
    public boolean deleteDishFromMenu(Menu menu, List<String> dishName) {
        return menuDAO.deleteDishFromMenu(menu, dishName);
    }

    @Transactional
    public ArrayList<String> getAllDish() {
        return menuDAO.getAllDish();
    }

    @Transactional
    public List<String> getAllDishFromMenu(Menu menu){
        return menuDAO.getAllDishFromMenu(menu);
    }

}
