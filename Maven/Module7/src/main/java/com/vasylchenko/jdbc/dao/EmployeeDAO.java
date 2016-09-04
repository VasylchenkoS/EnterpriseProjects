package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Cook;
import com.vasylchenko.jdbc.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();

    Employee getEmployeeBySurname(String employeeName);

    void addNewEmployee(Employee employee);

    void deleteEmployeeById(int Id);

    List<Cook> getAllCooks();
}
