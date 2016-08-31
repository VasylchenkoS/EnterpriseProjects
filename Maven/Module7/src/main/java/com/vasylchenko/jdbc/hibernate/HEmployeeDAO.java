package com.vasylchenko.jdbc.hibernate;

import com.vasylchenko.jdbc.dao.EmployeeDAO;
import com.vasylchenko.jdbc.model.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HEmployeeDAO implements EmployeeDAO {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployee() {
        return sessionFactory.getCurrentSession().createQuery("select e from Employee e").list();
    }

    @Override
    public Employee getEmployeeBySurname(String employeeSurname) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.surname like :surname");
        query.setParameter("surname", employeeSurname);
        return (Employee) query.uniqueResult();
    }

    @Override
    public void addNewEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Employee.class, employeeId));
        session.flush() ;
    }
}
