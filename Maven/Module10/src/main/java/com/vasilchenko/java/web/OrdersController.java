package com.vasilchenko.java.web;

import com.vasilchenko.java.model.Ordering;
import com.vasilchenko.java.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {
	@Autowired private OrderService orderService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView showAllOrders(ModelAndView model){
		model.setViewName("orders");
		model.addObject("orders", orderService.getAllOrders());
		return model;
	}

	@RequestMapping(value = "/orders/open", method = RequestMethod.GET)
	public ModelAndView showOpenOrders(ModelAndView model){
		model.setViewName("orders");
		model.addObject("orders", orderService.getAllClosedOrder());
		return model;
	}

	@RequestMapping(value = "/orders/closed", method = RequestMethod.GET)
	public ModelAndView showCloseOrders(ModelAndView model){
		model.setViewName("orders");
		model.addObject("orders", orderService.getAllOpenOrder());
		return model;
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity showOrderByID(@PathVariable("id") String id){
		Ordering ordering = orderService.getOrderById(Integer.parseInt(id));
		return new ResponseEntity(ordering, HttpStatus.OK);
	}

}
