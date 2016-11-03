package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.MenuDAO;
import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Menu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
@Repository
public class HMenuDAO implements MenuDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Menu> getAllMenu() {
        return sessionFactory.getCurrentSession().createQuery("select m from Menu m").list();
    }

    @Override
    public Menu findByName(String menuName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Menu d where d.menuName like :name");
        query.setParameter("name", menuName);
        return (Menu) query.uniqueResult();
    }

    @Override
    public Menu getMenuById(int id) {
        return sessionFactory.getCurrentSession().get(Menu.class, id);
    }

    @Override
    public void addNewMenu(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    public void deleteMenuById(int menuId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Menu.class, menuId));
        session.flush() ;
    }

    @Override
    public void updateMenu(Menu menu) {
        sessionFactory.getCurrentSession().update(menu);
    }

    @Override
    public void updateDishesInMenu(Menu menu, Set<Dish> dishList) {
        Set<Dish> dishSet = menu.getDishSet();
        dishList.forEach(dish -> {
            if(dishSet.contains(dish))
                dishSet.remove(dish);
            else dishSet.add(dish);
        });
        menu.setDishSet(dishSet);
        sessionFactory.getCurrentSession().update(menu);
    }

    @Override
    public List<Dish> getAllDishInMenu(int id) {
        Session session = sessionFactory.getCurrentSession();
        return new LinkedList<>(session.load(Menu.class, id).getDishSet());
    }
}
