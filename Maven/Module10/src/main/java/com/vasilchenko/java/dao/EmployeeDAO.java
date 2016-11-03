package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployee();

    Employee getEmployeeBySurname(String employeeName);

    void addNewEmployee(Employee employee);

    void deleteEmployeeById(int Id);

    void updateEmployee(Employee employee);

    List<Employee> getAllCooks();

    List<Employee> getAllWaiters();

    Employee getEmployeeById(int id);

    Employee getEmployeeByName(String employeeName);

    Employee getEmployeeByNameAndSurname(String employeeName, String employeeSurname);
}
