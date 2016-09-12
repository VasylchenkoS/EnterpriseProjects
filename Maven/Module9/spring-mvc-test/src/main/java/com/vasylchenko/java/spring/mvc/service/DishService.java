package com.vasylchenko.java.spring.mvc.service;

import com.vasylchenko.java.spring.mvc.dao.DishDAO;
import com.vasylchenko.java.spring.mvc.model.Dish;
import org.springframework.transaction.annotation.Transactional;

public class DishService {

	private DishDAO dishDAO;

	@Transactional
	public Dish getDishByName(String dishName){
		return dishDAO.findByName(dishName);
	}

	public void setDishDAO(DishDAO dishDAO) {
		this.dishDAO = dishDAO;
	}
}
