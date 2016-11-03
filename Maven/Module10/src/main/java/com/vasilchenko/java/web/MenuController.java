package com.vasilchenko.java.web;

import com.vasilchenko.java.model.Dish;
import com.vasilchenko.java.model.Menu;
import com.vasilchenko.java.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MenuController {
	@Autowired private MenuService menuService;

	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public ModelAndView showMenu(ModelAndView model){
		model.setViewName("menu");
		model.addObject("menus", menuService.getAllMenu());
		return model;
	}

	@RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
	public ResponseEntity showMenuByID(@PathVariable("id") String id){
		List<Dish> menu = menuService.getAllDishInMenu(Integer.parseInt(id));
		return new ResponseEntity(menu, HttpStatus.OK);
	}

	@RequestMapping(value = "/menus/find", method = RequestMethod.GET)
	public ResponseEntity findMenu(@RequestParam("menuNameText") String menuNameText){
		List<Menu> menus = menuService.getAllMenu();
		List<Menu> menuList;
		if (menuNameText.isEmpty())
			return new ResponseEntity("No Menu found for name: " + menuNameText, HttpStatus.NOT_FOUND);
		else
			menuList = menus.stream().filter(employee -> employee.getMenuName().toLowerCase().contains(menuNameText.toLowerCase())).collect(Collectors.toList());
		return new ResponseEntity(menuList, HttpStatus.OK);
	}
}
