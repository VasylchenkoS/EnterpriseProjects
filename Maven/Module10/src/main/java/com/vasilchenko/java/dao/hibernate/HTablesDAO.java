package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.TablesDAO;
import com.vasilchenko.java.model.Tables;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
