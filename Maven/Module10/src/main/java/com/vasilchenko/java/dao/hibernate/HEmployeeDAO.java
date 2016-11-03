package com.vasilchenko.java.dao.hibernate;

import com.vasilchenko.java.dao.EmployeeDAO;
import com.vasilchenko.java.model.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HEmployeeDAO implements EmployeeDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployee() {
        return sessionFactory.getCurrentSession()
                .createQuery("select e from Employee e").list();
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

    @Override
    public void updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAllCooks() {
        return sessionFactory.getCurrentSession().createQuery("" +
                "select e from Employee e where e.position='COOK'").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAllWaiters() {
        return sessionFactory.getCurrentSession().createQuery(
                "select e from Employee e where e.position='WAITER'").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Employee getEmployeeById(int id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Employee getEmployeeByName(String employeeName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.name like :name");
        query.setParameter("name", employeeName);
        return (Employee) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Employee getEmployeeByNameAndSurname(String employeeName, String employeeSurname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.name like :name and e.surname like :surname");
        query.setParameter("name", employeeName);
        query.setParameter("surname", employeeSurname);
        return (Employee) query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
