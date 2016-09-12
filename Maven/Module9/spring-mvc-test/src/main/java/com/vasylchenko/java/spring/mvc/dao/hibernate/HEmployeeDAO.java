package com.vasylchenko.java.spring.mvc.dao.hibernate;

import com.vasylchenko.java.spring.mvc.components.PositionController;
import com.vasylchenko.java.spring.mvc.dao.EmployeeDAO;
import com.vasylchenko.java.spring.mvc.model.Cook;
import com.vasylchenko.java.spring.mvc.model.Employee;
import com.vasylchenko.java.spring.mvc.model.Waiter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HEmployeeDAO implements EmployeeDAO {

    SessionFactory sessionFactory;
    PositionController positionController;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setPositionController(PositionController positionController) {
        this.positionController = positionController;
    }

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
        switch (employee.getPosition().getPosition()) {
            case "Waiter":
                Waiter waiter = new Waiter();
                waiter.setName(employee.getName());
                waiter.setSurname(employee.getSurname());
                waiter.setSalary(employee.getSalary());
                waiter.setPosition(employee.getPosition());
                waiter.setBirth(employee.getBirth());
                waiter.setPhone(employee.getPhone());
                sessionFactory.getCurrentSession().save(waiter);
                break;
            case "Cook":
                Cook cook = new Cook();
                cook.setName(employee.getName());
                cook.setSurname(employee.getSurname());
                cook.setSalary(employee.getSalary());
                cook.setPosition(employee.getPosition());
                cook.setBirth(employee.getBirth());
                cook.setPhone(employee.getPhone());
                sessionFactory.getCurrentSession().save(cook);
                break;
            default:
                sessionFactory.getCurrentSession().save(employee);
                break;
        }
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.load(Employee.class, employeeId));
        session.flush() ;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cook> getAllCooks() {
        return sessionFactory.getCurrentSession().createQuery("" +
                "select e from Employee e where e.position=" +
                "(select p from Position p where p.position='Cook')").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAllWaiters() {
        return sessionFactory.getCurrentSession().createQuery(
                "select e from Employee e where e.position=" +
                "(select p from Position p where p.position='Waiter')").list();
    }
}
