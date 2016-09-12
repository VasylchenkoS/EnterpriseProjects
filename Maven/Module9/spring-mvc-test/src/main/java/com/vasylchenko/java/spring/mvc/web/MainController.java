package com.vasylchenko.java.spring.mvc.web;

import com.vasylchenko.java.spring.mvc.service.EmployeeService;
import com.vasylchenko.java.spring.mvc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private EmployeeService employeeService;
	private MenuService menuService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getAllEmployees(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		modelAndView.addObject("employees" , employeeService.getWaiters());
		modelAndView.addObject("menus" , menuService.getAllMenu());
		return modelAndView;
	}

	@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")  // 404
	public class OrderNotFoundException extends RuntimeException {
		public OrderNotFoundException(String message) {
			super(message);
		}
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView getAllMenus(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("main");
//		modelAndView.addObject("menus" , menuService.getAllMenu());
//		return modelAndView;
//	}

	@Autowired
	public void setMenuService(MenuService menuService) {this.menuService = menuService;}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}
}
