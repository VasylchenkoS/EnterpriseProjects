package com.vasylchenko.java.spring.mvc.dao.hibernate;

import com.vasylchenko.java.spring.mvc.dao.CategoryDAO;
import com.vasylchenko.java.spring.mvc.model.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HCategoryDAO implements CategoryDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategory() {
        return sessionFactory.getCurrentSession().createQuery("select c from Category c").list();
    }

    @Override
    public Category getCategoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from Category c where c.category like :name");
        query.setParameter("name", name);
        return (Category) query.uniqueResult();
    }
}
