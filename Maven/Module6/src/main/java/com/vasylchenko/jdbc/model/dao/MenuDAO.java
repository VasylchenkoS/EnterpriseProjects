package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Menu;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public interface MenuDAO {

    void setDataSource(DataSource dataSources);

    List<Menu> getAll();

    List<Menu> findByName(String menuName);

    boolean addNewMenu(Menu menu);

    boolean deleteMenuById(int Id);

    List<Menu> selectMenu(String column, String value);

    boolean checkMenu(Menu menu);

    boolean addDishIntoMenu(Menu menu, List<String> dishName);

    boolean deleteDishFromMenu(Menu menu, List<String> dishName);

    ArrayList<String> getAllDish();

    ArrayList<String> getAllDishFromMenu(Menu menu);

}
