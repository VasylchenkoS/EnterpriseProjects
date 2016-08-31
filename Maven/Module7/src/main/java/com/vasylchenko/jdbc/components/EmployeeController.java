package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Employee;
import com.vasylchenko.jdbc.dao.EmployeeDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

    public Employee getEmployeeBySurname(String surname) {
        return employeeDAO.getEmployeeBySurname(surname);
    }

    public void addNewEmployee(Employee employee){
         employeeDAO.addNewEmployee(employee);
    }

    public void deleteEmployeeById(int Id){
         employeeDAO.deleteEmployeeById(Id);
    }

}
