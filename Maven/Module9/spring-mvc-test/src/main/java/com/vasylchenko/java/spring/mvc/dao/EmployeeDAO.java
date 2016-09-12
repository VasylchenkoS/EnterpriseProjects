package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Cook;
import com.vasylchenko.java.spring.mvc.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();

    Employee getEmployeeBySurname(String employeeName);

    void addNewEmployee(Employee employee);

    void deleteEmployeeById(int Id);

    List<Cook> getAllCooks();

    List<Employee> getAllWaiters();
}
