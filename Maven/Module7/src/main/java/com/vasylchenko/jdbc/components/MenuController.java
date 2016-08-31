package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.MenuDAO;
import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public class MenuController {

    MenuDAO menuDAO;

    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public List<Menu> getAllMenu() {
        return menuDAO.getAllMenu();
    }

    public Menu findByName(String menuName) {
        return menuDAO.findByName(menuName);
    }

    public void addNewMenu(Menu menu) {
        menuDAO.addNewMenu(menu);
    }

    public void deleteMenuById(int id) {
        menuDAO.deleteMenuById(id);
    }

    public void updateDishesInMenu(Menu menu, Set<Dish> dishName) {
        menuDAO.updateDishesInMenu(menu, dishName);
    }
}