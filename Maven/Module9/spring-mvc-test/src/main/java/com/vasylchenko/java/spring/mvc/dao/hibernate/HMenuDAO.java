package com.vasylchenko.java.spring.mvc.dao.hibernate;

import com.vasylchenko.java.spring.mvc.dao.MenuDAO;
import com.vasylchenko.java.spring.mvc.model.Dish;
import com.vasylchenko.java.spring.mvc.model.Menu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;

public class HMenuDAO implements MenuDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
}
