package com.vasylchenko.java.spring.mvc.dao.hibernate;

import com.vasylchenko.java.spring.mvc.dao.TablesDAO;
import com.vasylchenko.java.spring.mvc.model.Tables;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HTablesDAO implements TablesDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tables> getAllTables() {
        return sessionFactory.getCurrentSession().createQuery("select t from Tables t").list();
    }

    @Override
    public Tables getTableByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select t from Tables t where t.number like :number");
        query.setParameter("number", name);
        return (Tables) query.uniqueResult();
    }
}
