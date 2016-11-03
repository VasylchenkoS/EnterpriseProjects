package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.StorageDAO;
import com.vasilchenko.java.model.Storage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HStorageDAO implements StorageDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Storage> getAllIngredient() {
        return sessionFactory.getCurrentSession().createQuery("select i from Storage i").list();
    }

    @Override
    public void addNewIngredient(Storage ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    public void deleteIngredientById(int ingredientId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Storage.class, ingredientId));
        session.flush() ;
    }

    @Override
    public void updateIngredient(Storage ingredient) {
        sessionFactory.getCurrentSession().update(ingredient);
    }

    @Override
    public void changeIngredientCount(Storage ingredient, long count) {
        ingredient.setQuantity(count);
        sessionFactory.getCurrentSession().update(ingredient);
    }

    @Override
    public Storage getIngredientByName(String ingredientName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from Storage s where s.ingredientName like :name");
        query.setParameter("name", ingredientName);
        return (Storage) query.uniqueResult();
    }

    @Override
    public Storage getIngredientById(int id) {
        return sessionFactory.getCurrentSession().get(Storage.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Storage> checkIngredientCount() {
        return sessionFactory.getCurrentSession().createQuery("select i from Storage i where i.quantity < 50").list();
    }
}
