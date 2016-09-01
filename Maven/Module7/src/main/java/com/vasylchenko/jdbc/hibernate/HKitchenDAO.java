package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.components.KitchenDishStateController;
import com.vasylchenko.jdbc.dao.KitchenDAO;
import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.Employee;
import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.Ordering;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Types;
import java.util.List;

public class HKitchenDAO implements KitchenDAO {

    SessionFactory sessionFactory;
    KitchenDishStateController kitchenDishStateController;

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
                "select c from Kitchen c where c.status=" +
                        "(select ks from KitchenDishState ks where ks.state='Cooking')"
        ).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Kitchen> getAllReadyDish() {
        return sessionFactory.getCurrentSession().createQuery(
                "select c from Kitchen c where c.status=" +
                        "(select ks from KitchenDishState ks where ks.state='Ready')"
        ).list();
    }

    @Override
    public void addAllDishFromOrder(Ordering order) {
        Session session = sessionFactory.getCurrentSession();
//        Kitchen kitchen = new Kitchen(
//                null, order, ,
//                null, null);
        for (Dish dish : order.getDishList()) {
            Kitchen kitchen = new Kitchen();
            Query query = session.createQuery(
                    "select e from Employee e where e.id = :id");
            query.setParameter("id", getCookId());
            kitchen.setCook((Employee) query.uniqueResult());
            kitchen.setOrder(order);
            kitchen.setStatus(kitchenDishStateController.getKitchenDishStateByName("Cooking"));
            kitchen.setDate(new Date(new java.util.Date().getTime()));
            kitchen.setDishName(dish);
            sessionFactory.getCurrentSession().save(kitchen);
        }
    }

    private int getCookId() {
        final int[] result = new int[1];
        sessionFactory.getCurrentSession().doWork(connection -> {
            try (CallableStatement function = connection
                    .prepareCall("{ ? = call choose_cook() }")) {
                function.registerOutParameter(1, Types.INTEGER);
                function.execute();
                result[0] = function.getInt(1);
            }});
        return result[0];
    }

    @Override
    public void setDishReady(Kitchen kitchen) {
        Session session = sessionFactory.getCurrentSession();
        kitchen.setStatus(kitchenDishStateController.getKitchenDishStateByName("Ready"));
        session.update(kitchen);
    }
}
