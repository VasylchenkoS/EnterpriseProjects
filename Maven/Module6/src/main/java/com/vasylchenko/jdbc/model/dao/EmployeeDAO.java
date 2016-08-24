package com.vasylchenko.jdbc.model.dao;

import com.vasylchenko.jdbc.model.Employee;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO {

    void setDataSource(DataSource dataSources);

    List<Employee> getAll();

    List<Employee> findByName(String employeeName);

    boolean addNewEmployee(Employee employee);

    boolean deleteEmployeeById(int Id);

    List<String> selectEmployee(String column, String value);

    boolean checkEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    ArrayList<String> getAllPositions();
}
