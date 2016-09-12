package com.vasylchenko.java.spring.mvc.web;

import com.vasylchenko.java.spring.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {this.employeeService = employeeService;}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String employees(Map<String, Object> model){
//		model.put("employees", employeeService.getEmployee());
//		return "main";
//	}

//	@RequestMapping(value = "/employeePage", method = RequestMethod.GET)
//	public ModelAndView employee(@RequestParam("employeeName") String employeeName){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("employeePage");
//		modelAndView.addObject("employee" , employeeService.getEmployeeByName(employeeName));
//		return modelAndView;
//	}

//	@RequestMapping(value = "/main/${employeeSurname}", method = RequestMethod.GET)
//	public ModelAndView employee(@PathVariable String employeeSurname){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("employeePage");
//		modelAndView.addObject("employee" , employeeService.getEmployeeByName(employeeSurname));
//		return modelAndView;
//	}


}
