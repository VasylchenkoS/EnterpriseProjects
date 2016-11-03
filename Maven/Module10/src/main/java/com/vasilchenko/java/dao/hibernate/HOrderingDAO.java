package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.OrderingDAO;
import com.vasilchenko.java.components.OrderState;
import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Ordering;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HOrderingDAO implements OrderingDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addNewOrder(Ordering order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void addDishesInOrder(Ordering order, List<Dish> dishList) {
        if (order.getOrderState().equals(OrderState.OPEN)) {
            order.setDishList(dishList);
            sessionFactory.getCurrentSession().update(order);
        } else {
            throw new RuntimeException("You can't update Closed Order");
        }
    }

    @Override
    public void removeDishesFromOrder(Ordering order, List<Dish> dishList) {
        if (order.getOrderState().equals(OrderState.OPEN)) {
            dishList.forEach(dish -> {
                order.getDishList().contains(dish);
                order.getDishList().remove(dish);
            });
            sessionFactory.getCurrentSession().update(order);
        } else {
            throw new RuntimeException("You can't update Closed Order");
        }
    }

    @Override
    public void deleteOrder(Ordering order) {
        if (order.getOrderState().equals(OrderState.OPEN)) {
            sessionFactory.getCurrentSession().delete(order);
        }
        else {
            throw new RuntimeException("You can't delete Closed Order");
        }
    }

    @Override
    public void closeOrder(Ordering order) {
        order.setOrderState(OrderState.CLOSED);
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ordering> showOpenOrder() {
        return sessionFactory.getCurrentSession().createQuery(
                "select o from Ordering o where o.orderState='OPEN'").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ordering> showClosedOrder() {
        return sessionFactory.getCurrentSession().createQuery(
                "select o from Ordering o where o.orderState='CLOSED'").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ordering> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery(
                "select o from Ordering o").list();
    }

    @Override
    public Ordering getOrderById(int id) {
        return sessionFactory.getCurrentSession().load(Ordering.class, id);
    }
}