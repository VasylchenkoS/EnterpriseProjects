package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.KitchenHistoryDAO;
import com.vasilchenko.java.components.KitchenDishState;
import com.vasilchenko.java.model.Kitchen;
import com.vasilchenko.java.model.KitchenHistory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class HKitchenHistoryDAO implements KitchenHistoryDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveReadyDish(Kitchen kitchen) {
        sessionFactory.getCurrentSession().delete(kitchen);
        KitchenHistory kitchenHistory = new KitchenHistory();
        kitchenHistory.setCook(kitchen.getCook());
        kitchenHistory.setDishName(kitchen.getDishName());
        kitchenHistory.setOrder(kitchen.getOrder());
        kitchenHistory.setDate(new Date(new java.util.Date().getTime()));
        kitchenHistory.setKitchenDishState(KitchenDishState.READY);
        sessionFactory.getCurrentSession().save(kitchenHistory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KitchenHistory> getAllReadyDish() {
        return sessionFactory.getCurrentSession().createQuery(
                "select c from Kitchen c").list();
    }
}