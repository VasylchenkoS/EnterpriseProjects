package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.components.OrderStateController;
import com.vasylchenko.jdbc.dao.OrderingDAO;
import com.vasylchenko.jdbc.model.Dish;
import com.vasylchenko.jdbc.model.Ordering;
import org.hibernate.SessionFactory;

import java.util.List;

public class HOrderingDAO implements OrderingDAO {

    SessionFactory sessionFactory;
    OrderStateController orderStateController;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setOrderStateController(OrderStateController orderStateController) {
        this.orderStateController = orderStateController;
    }

    @Override
    public void addNewOrder(Ordering order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void addDishesInOrder(Ordering order, List<Dish> dishList) {
        if (order.getOrderState().equals(orderStateController.getStateByName("Close"))) {
            order.setDishList(dishList);
            sessionFactory.getCurrentSession().update(order);
        } else {
            throw new RuntimeException("You can't update Closed Order");
        }
    }

    @Override
    public void removeDishesFromOrder(Ordering order, List<Dish> dishList) {
        if (order.getOrderState().equals(orderStateController.getStateByName("Close"))) {
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
        if (order.getOrderState().equals(orderStateController.getStateByName("Close")))
            sessionFactory.getCurrentSession().delete(order);
        else {
            throw new RuntimeException("You can't delete Closed Order");
        }
    }

    @Override
    public void closeOrder(Ordering order) {
        order.setOrderState(orderStateController.getStateByName("Close"));
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ordering> showOpenOrder() {
        return sessionFactory.getCurrentSession().createQuery(
                "select o from Ordering o where o.orderState=" +
                "(select os from OrderState os where os.state='Open')").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ordering> showClosedOrder() {
        return sessionFactory.getCurrentSession().createQuery(
                "select o from Ordering o where o.orderState=" +
                        "(select os from OrderState os where os.state='Close')").list();

    }
}