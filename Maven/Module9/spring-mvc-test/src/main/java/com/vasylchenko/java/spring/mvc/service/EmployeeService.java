package com.vasylchenko.java.spring.mvc.service;

import com.vasylchenko.java.spring.mvc.dao.EmployeeDAO;
import com.vasylchenko.java.spring.mvc.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeService {

	private EmployeeDAO employeeDAO;

	@Transactional
	public List<Employee> getEmployee(){
		return employeeDAO.getAllEmployee();
	}

	@Transactional
	public Employee getEmployeeByName(String employeeName) {
		return employeeDAO.getEmployeeBySurname(employeeName);
	}

	@Transactional
	public List<Employee> getWaiters() {
		return employeeDAO.getAllWaiters();
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}

}
