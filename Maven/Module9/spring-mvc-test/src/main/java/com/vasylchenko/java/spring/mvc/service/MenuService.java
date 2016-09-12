package com.vasylchenko.java.spring.mvc.service;

import com.vasylchenko.java.spring.mvc.dao.MenuDAO;
import com.vasylchenko.java.spring.mvc.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MenuService {

	private MenuDAO menuDAO;

	@Transactional
	public List<Menu> getAllMenu(){
		return menuDAO.getAllMenu();
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
}
