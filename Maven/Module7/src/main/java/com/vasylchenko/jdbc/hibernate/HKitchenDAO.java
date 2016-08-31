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
        Kitchen kitchen = new Kitchen(
                null, order, kitchenDishStateController.getKitchenDishStateByName("Open"),
                null, null);
        order.getDishList().forEach(dish -> {
            final int[] result = new int[1];
            session.doWork(connection -> {
                try (CallableStatement function = connection
                        .prepareCall("{ ? = call choose_cook() }")) {
                    function.registerOutParameter(1, Types.INTEGER);
                    result[0] = function.getInt(1);
                }});
            Query query = session.createQuery(
                    "select e from Employee e where e.id = :id");
            query.setParameter("id", result[0]);
            kitchen.setCook((Employee) query.uniqueResult());
            kitchen.setDishName(dish);
            kitchen.setDate(new Date(new java.util.Date().getTime()));
            sessionFactory.getCurrentSession().save(kitchen);
        });
    }

    @Override
    public void setDishReady(Ordering order, Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select k from Kitchen k where " +
                "k.dishName = :dish and k.order = :cur_order " +
                "and k.status=" +
                "(select ks from KitchenDishState ks where ks.state='Cooking')");
        query.setParameter("dish", dish);
        query.setParameter("cur_order", order);
        Kitchen kitchen = (Kitchen) query.uniqueResult();
        kitchen.setStatus(kitchenDishStateController.getKitchenDishStateByName("Ready"));
        session.save(kitchen);
    }
}
