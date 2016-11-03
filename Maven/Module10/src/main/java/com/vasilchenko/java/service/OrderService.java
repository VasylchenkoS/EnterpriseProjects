package com.vasilchenko.java.service;


import com.vasilchenko.java.dao.OrderingDAO;
import com.vasilchenko.java.model.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderService {

	@Autowired OrderingDAO orderingDAO;

	@Transactional
	public List<Ordering> getAllOrders() {
		List<Ordering> orderings = new ArrayList<>();
		orderings.addAll(getAllOpenOrder());
		orderings.addAll(getAllClosedOrder());
		return orderings;
	}

	@Transactional
	public List<Ordering> getAllOpenOrder(){
		return orderingDAO.showOpenOrder();
	}

	@Transactional
	public List<Ordering> getAllClosedOrder(){
		return orderingDAO.showClosedOrder();
	}

	@Transactional
	public Ordering getOrderById(int id){
		return orderingDAO.getOrderById(id);
	}

	public void setOrderingDAO(OrderingDAO orderingDAO) {this.orderingDAO = orderingDAO;}
}
