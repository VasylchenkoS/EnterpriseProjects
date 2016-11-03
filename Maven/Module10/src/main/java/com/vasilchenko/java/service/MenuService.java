package com.vasilchenko.java.service;


import com.vasilchenko.java.dao.MenuDAO;
import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MenuService {

	@Autowired private MenuDAO menuDAO;

	@Transactional
	public List<Menu> getAllMenu(){
		return menuDAO.getAllMenu();
	}

	@Transactional
	public List<Dish> getAllDishInMenu(int id){
		return menuDAO.getAllDishInMenu(id);
	}

	@Transactional
	public Menu findMenuByName(String menuName){
		return menuDAO.findByName(menuName);
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
}
