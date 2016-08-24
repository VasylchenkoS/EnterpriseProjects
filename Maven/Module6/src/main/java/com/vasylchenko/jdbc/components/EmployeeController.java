package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.model.Employee;
import com.vasylchenko.jdbc.model.dao.EmployeeDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeController {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAll();
    }


    @Transactional
    public List<Employee> getEmployeeByName(String name) {
        return employeeDAO.findByName(name);
    }

    @Transactional
    public boolean addNewEmployee(Employee employee){
        return employeeDAO.addNewEmployee(employee);
    }

    @Transactional
    public boolean deleteEmployeeById(int Id){
        return employeeDAO.deleteEmployeeById(Id);
    }

    @Transactional
    public List<String> selectEmployee(String column, String value){
        return employeeDAO.selectEmployee(column,value);
    }

    @Transactional
    public boolean checkEmployee(Employee employee){
        return employeeDAO.checkEmployee(employee);
    }

    @Transactional
    public boolean updateEmployee(Employee employee){
        return employeeDAO.updateEmployee(employee);
    }

    @Transactional
    public List<String> getAllPositions() {
        return employeeDAO.getAllPositions();
    }
}
