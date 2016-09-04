package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.components.KitchenDishStateController;
import com.vasylchenko.jdbc.components.KitchenHistoryController;
import com.vasylchenko.jdbc.dao.KitchenDAO;
import com.vasylchenko.jdbc.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

public class HKitchenDAO implements KitchenDAO {

    SessionFactory sessionFactory;
    KitchenDishStateController kitchenDishStateController;
    KitchenHistoryController kitchenHistoryController;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setKitchenDishStateController(KitchenDishStateController kitchenDishStateController) {
        this.kitchenDishStateController = kitchenDishStateController;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Kitchen> getAllCookingDish() {
        return sessionFactory.getCurrentSession().createQuery(
                "select c from Kitchen c").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KitchenHistory> getAllReadyDish() {
        return kitchenHistoryController.getAllReadyDish();
    }

    @Override
    public void addAllDishFromOrder(Ordering order) {
        Session session = sessionFactory.getCurrentSession();
        for (Dish dish : order.getDishList()) {
            Kitchen kitchen = new Kitchen();
            Query query = session.createQuery(
                    "select e from Employee e where e.id = :id");
//            query.setParameter("id", getCookId());
            query.setParameter("id", 3);
            kitchen.setCook((Employee) query.uniqueResult());
            kitchen.setOrder(order);
            kitchen.setStatus(kitchenDishStateController.getKitchenDishStateByName("Cooking"));
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