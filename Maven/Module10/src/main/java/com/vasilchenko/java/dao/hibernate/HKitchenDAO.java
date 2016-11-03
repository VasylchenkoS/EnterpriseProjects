package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.components.KitchenDishState;
import com.vasilchenko.java.components.KitchenHistoryController;
import com.vasilchenko.java.dao.KitchenDAO;
import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Employee;
import com.vasilchenko.java.model.Kitchen;
import com.vasilchenko.java.model.Ordering;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class HKitchenDAO implements KitchenDAO {

    SessionFactory sessionFactory;
    KitchenHistoryController kitchenHistoryController;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Kitchen> getAllCookingDish() {
        return sessionFactory.getCurrentSession().createQuery(
                "select c from Kitchen c").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<? extends Kitchen> getAllReadyDish() {
        return kitchenHistoryController.getAllReadyDish();
    }

    @Override
    public void addAllDishFromOrder(Ordering order) {
        Session session = sessionFactory.getCurrentSession();
        for (Dish dish : order.getDishList()) {
            Kitchen kitchen = new Kitchen();
            Query query = session.createQuery(
                    "select e from Employee e where e.id = :id");
            query.setParameter("id", 3);
            kitchen.setCook((Employee) query.uniqueResult());
            kitchen.setOrder(order);
            kitchen.setKitchenDishState(KitchenDishState.COOKING);
            kitchen.setDate(new Date(new java.util.Date().getTime()));
            kitchen.setDishName(dish);
            sessionFactory.getCurrentSession().save(kitchen);
        }
    }

    @Override
    public void setDishReady(Kitchen kitchen) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(kitchen);
        kitchenHistoryController.saveReadyDish(kitchen);
    }
}