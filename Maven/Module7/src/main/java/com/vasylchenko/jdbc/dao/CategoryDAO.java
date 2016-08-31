package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategory();

    Category getCategoryByName(String name);
}
