package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.dao.OrderStateDAO;
import com.vasylchenko.jdbc.model.OrderState;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HOrderStateDAO implements OrderStateDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderState> getAllStates() {
        return sessionFactory.getCurrentSession().createQuery("select s from OrderState s").list();
    }

    @Override
    public OrderState getStateByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from OrderState s where s.state like :state");
        query.setParameter("state", name);
        return (OrderState) query.uniqueResult();
    }
}
