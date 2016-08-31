package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.dao.PositionDAO;
import com.vasylchenko.jdbc.model.Position;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HPositionDAO implements PositionDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Position> getAllPositions() {
        return sessionFactory.getCurrentSession().createQuery("select p from Position p").list();
    }

    @Override
    public Position getPositionByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from Position p where p.position like :name");
        query.setParameter("name", name);
        return (Position) query.uniqueResult();
    }
}
