package com.vasilchenko.java.web;


import com.vasilchenko.java.service.EmployeeService;
import com.vasilchenko.java.service.MenuService;
import com.vasilchenko.java.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired private EmployeeService employeeService;
	@Autowired private MenuService menuService;
	@Autowired private OrderService orderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getAllEmployees(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		modelAndView.addObject("menu", menuService.getAllMenu());
		modelAndView.addObject("employees" , employeeService.getWaiters());
		modelAndView.addObject("order", orderService.getAllOrders());
		return modelAndView;
	}
}
