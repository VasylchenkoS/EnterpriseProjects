package com.vasylchenko.java.spring.mvc.web;

import com.vasylchenko.java.spring.mvc.model.Dish;
import com.vasylchenko.java.spring.mvc.service.DishService;
import com.vasylchenko.java.spring.mvc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

	private MenuService menuService;
	private DishService dishService;

//	@RequestMapping(value = "/employeePage", method = RequestMethod.GET)
//	public ModelAndView employee(@RequestParam("employeeName") String employeeName){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("employeePage");
//		modelAndView.addObject("employee" , employeeService.getEmployeeByName(employeeName));
//		return modelAndView;
//	}

	@RequestMapping(value = "/dish", method = RequestMethod.GET)
	public ModelAndView showDishInformationPage(@RequestParam String dishName) throws MainController.OrderNotFoundException {
		Dish dish = dishService.getDishByName(dishName);
		ModelAndView modelAndView = new ModelAndView();
		if (dish != null) {
			modelAndView.setViewName("dish");
			modelAndView.addObject("dish", dish);
		}return modelAndView;
	}
//
//	@RequestMapping(value = "/search/{dishFind}", method = RequestMethod.POST)
//	public ModelAndView search(@PathVariable("dishFind") String dishName){
//		if(dishName != null) {
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("dish");
//			modelAndView.addObject("dish", dishService.getDishByName(dishName));
//			return modelAndView;
//		}
//		return null;
//	}

	@Autowired
	public void setMenuService(MenuService menuService) {this.menuService = menuService;}

	@Autowired
	public void setDishService(DishService dishService) {this.dishService = dishService;}
}
