package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.components.KitchenDishStateController;
import com.vasylchenko.jdbc.dao.KitchenHistoryDAO;
import com.vasylchenko.jdbc.model.Kitchen;
import com.vasylchenko.jdbc.model.KitchenHistory;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

public class HKitchenHistoryDAO implements KitchenHistoryDAO {

    SessionFactory sessionFactory;
    KitchenDishStateController kitchenDishStateController;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setKitchenDishStateController(KitchenDishStateController kitchenDishStateController) {
        this.kitchenDishStateController = kitchenDishStateController;
    }

    @Override
    public void saveReadyDish(Kitchen kitchen) {
        sessionFactory.getCurrentSession().delete(kitchen);
        KitchenHistory kitchenHistory = new KitchenHistory();
        kitchenHistory.setCook(kitchen.getCook());
        kitchenHistory.setDishName(kitchen.getDishName());
        kitchenHistory.setOrder(kitchen.getOrder());
        kitchenHistory.setDate(new Date(new java.util.Date().getTime()));
        kitchenHistory.setStatus(kitchenDishStateController.getKitchenDishStateByName("Ready"));
        sessionFactory.getCurrentSession().save(kitchenHistory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KitchenHistory> getAllReadyDish() {
        return sessionFactory.getCurrentSession().createQuery(
                "select c from Kitchen c").list();
    }
}
