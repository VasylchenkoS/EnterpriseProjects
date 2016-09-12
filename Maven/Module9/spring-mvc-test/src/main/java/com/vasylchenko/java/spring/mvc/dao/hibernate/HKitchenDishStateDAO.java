package com.vasylchenko.java.spring.mvc.dao.hibernate;

import com.vasylchenko.java.spring.mvc.dao.KitchenDishStateDAO;
import com.vasylchenko.java.spring.mvc.model.KitchenDishState;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HKitchenDishStateDAO implements KitchenDishStateDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<KitchenDishState> getAllKitchenDishStates() {
        return sessionFactory.getCurrentSession().createQuery("select k from KitchenDishState k").list();
    }

    @Override
    public KitchenDishState getKitchenDishStateByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select k from KitchenDishState k where k.state like :name");
        query.setParameter("name", name);
        return (KitchenDishState) query.uniqueResult();
    }
}
