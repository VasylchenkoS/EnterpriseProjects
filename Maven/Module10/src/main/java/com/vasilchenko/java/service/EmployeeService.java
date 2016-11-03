package com.vasilchenko.java.service;


import com.vasilchenko.java.dao.EmployeeDAO;
import com.vasilchenko.java.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeService {

	@Autowired private EmployeeDAO employeeDAO;

	@Transactional
	public List<Employee> getEmployee(){
		return employeeDAO.getAllEmployee();
	}

	@Transactional
	public Employee getEmployeeById(int id){
		return employeeDAO.getEmployeeById(id);
	}

	@Transactional
	public Employee getEmployeeByName(String employeeName) {
		return employeeDAO.getEmployeeByName(employeeName);
	}

	@Transactional
	public Employee getEmployeeBySurname(String employeeSurname) {
		return employeeDAO.getEmployeeBySurname(employeeSurname);
	}

	@Transactional
	public Employee getEmployeeByNameAndSurname(String employeeName, String employeeSurname) {
		return employeeDAO.getEmployeeByNameAndSurname(employeeName, employeeSurname);
	}

	@Transactional
	public List<Employee> getWaiters() {
		return employeeDAO.getAllWaiters();
	}

}
