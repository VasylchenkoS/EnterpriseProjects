package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.CategoryDAO;
import com.vasylchenko.jdbc.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CategoryController {

    CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }

    public Category getCategoryByName(String name) {
        return categoryDAO.getCategoryByName(name);
    }
}
