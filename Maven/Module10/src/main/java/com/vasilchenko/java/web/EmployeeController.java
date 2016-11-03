package com.vasilchenko.java.web;

import com.vasilchenko.java.model.Employee;
import com.vasilchenko.java.service.EmployeeService;
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
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;


	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView showEmployee(ModelAndView model){
		model.setViewName("employees");
		model.addObject("employees", employeeService.getEmployee());
		return model;
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public ResponseEntity showEmployeeById(@PathVariable("id") String id){
		Employee employee = employeeService.getEmployeeById(Integer.parseInt(id));
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employees/find", method = RequestMethod.GET)
	public ResponseEntity findEmployee(@RequestParam("employeeNameText") String employeeNameText,
									   @RequestParam("employeeSurnameText") String employeeSurnameText){
		List<Employee> employees = employeeService.getEmployee();
		List<Employee> employeeList = null;
		if (employeeNameText.isEmpty() & employeeSurnameText.isEmpty())
			return new ResponseEntity("No Customer found for name: " + employeeNameText + "and/or Surname: " + employeeSurnameText, HttpStatus.NOT_FOUND);
		if (!employeeNameText.isEmpty() & !employeeSurnameText.isEmpty()) {
			employeeList = employees.stream().filter(employee -> employee.getName().toLowerCase().contains(employeeNameText.toLowerCase())
					& employee.getSurname().toLowerCase().contains(employeeSurnameText.toLowerCase())).collect(Collectors.toList());
		} else if (!employeeNameText.isEmpty())
			employeeList = employees.stream().filter(employee -> employee.getName().toLowerCase().contains(employeeNameText.toLowerCase())).collect(Collectors.toList());
		else if (!employeeSurnameText.isEmpty())
			employeeList = employees.stream().filter(employee -> employee.getSurname().toLowerCase().contains(employeeSurnameText.toLowerCase())).collect(Collectors.toList());
		return new ResponseEntity(employeeList, HttpStatus.OK);
	}
}
