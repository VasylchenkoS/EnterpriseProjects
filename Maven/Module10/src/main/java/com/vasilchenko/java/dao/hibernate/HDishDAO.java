package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.DishDAO;
import com.vasilchenko.java.model.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HDishDAO implements DishDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dish> getAllDish() {
        return sessionFactory.getCurrentSession().createQuery("select d from Dish d").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Dish findByName(String dishName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name like :name");
        query.setParameter("name", dishName);
        return (Dish) query.uniqueResult();
    }

    @Override
    public Dish findById(int id) {
        return sessionFactory.getCurrentSession().get(Dish.class, id);
    }

    @Override
    public void updateDish(Dish dish) {
        sessionFactory.getCurrentSession().update(dish);
    }

    @Override
    public void addNewDish(Dish dish) {
        sessionFactory.getCurrentSession().save(dish);

    }

    @Override
    public void deleteDishById(int dishId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Dish.class, dishId));
        session.flush() ;
    }
}
