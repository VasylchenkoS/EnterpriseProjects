package com.vasylchenko.jdbc.jdbc_dao;

import com.vasylchenko.jdbc.model.Employee;
import com.vasylchenko.jdbc.model.dao.EmployeeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDAO implements EmployeeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDAO.class);
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT id_employee, surname, name, birth, phone, salary, position\n" +
                    "FROM employee INNER JOIN position ON employee.id_position = position.id_position";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) result.add(createEmployee(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> findByName(String employeeName) {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id_employee, surname, name, birth, phone, salary, position " +
                             "FROM employee INNER JOIN position ON employee.id_position = position.id_position " +
                             "WHERE name = ?")) {
            statement.setString(1, employeeName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(createEmployee(resultSet));
            if (result.isEmpty())
                throw new RuntimeException("Cannot find Employee with name " + employeeName);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Transactional
    public boolean addNewEmployee(Employee employee) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO employee (Surname, Name, Birth, Phone, ID_Position, Salary) VALUES " +
                             "(?,?,?,?, (SELECT ID_Position FROM Position WHERE Position=?), ?)")) {
            statement.setString(1, employee.getSurname());
            statement.setString(2, employee.getName());
            statement.setDate(3, employee.getBirth());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getPosition());
            statement.setFloat(6, employee.getSalary());
            if (!statement.execute()){
                LOGGER.info("Employee :\n" + employee.toString() + "\n successfully added");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteEmployeeById(int Id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id_employee = ?")) {
            Employee employee = getAll().stream().
                    filter(e -> e.getId()==Id).findAny().orElse(null);
            statement.setInt(1, Id);
            if (employee != null & !statement.execute()) {
                LOGGER.info("Employee :\n" + employee.toString() + "\n successfully deleted");
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<String> selectEmployee(String column, String value){
        List<String> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id_employee, surname, name, birth, phone, salary, position " +
                             "FROM employee INNER JOIN position ON employee.id_position = position.id_position " +
                             "WHERE "+ column +" = ?")) {
            statement.setString(1   , value);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) result.add(resultSet.getString("position"));
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean checkEmployee(Employee employee) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM employee WHERE id_employee = ?")) {
            statement.setInt(1, employee.getId());
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employee SET name=?,surname=?, birth=?, phone=?, " +
                             "id_position=(SELECT ID_Position FROM Position WHERE Position=?),salary=? WHERE id_employee=?")) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setDate(3, employee.getBirth());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getPosition());
            statement.setFloat(6, employee.getSalary());
            statement.setInt(7, employee.getId());
            if (statement.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB.");
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<String> getAllPositions(){
        ArrayList<String> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT position FROM position");
            while (resultSet.next()) result.add(resultSet.getString("Position"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("id_employee"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getDate("birth"),
                resultSet.getString("phone"),
                resultSet.getString("position"),
                resultSet.getFloat("salary"));
    }
}