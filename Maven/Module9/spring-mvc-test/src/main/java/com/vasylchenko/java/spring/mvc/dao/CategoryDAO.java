package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategory();

    Category getCategoryByName(String name);
}
