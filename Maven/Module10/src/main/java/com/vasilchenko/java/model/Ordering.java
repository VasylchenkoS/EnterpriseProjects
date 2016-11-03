package com.vasilchenko.java.model;

import com.vasilchenko.java.components.OrderState;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "ordering")
public class Ordering {
    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_order")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn (name = "id_table")
    private Tables table;

    @Column(name = "date")
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_dish",
    joinColumns = @JoinColumn(name = "id_order"),
    inverseJoinColumns = @JoinColumn(name = "id_dish"))
    private List<Dish> dishList = new LinkedList<>();

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public Ordering() {
    }

    public Ordering(Employee employee, Tables table, Date date, List<Dish> dishList, OrderState orderState) {
        this.employee = employee;
        this.table = table;
        this.date = date;
        this.dishList = dishList;
        this.orderState = orderState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordering)) return false;

        Ordering ordering = (Ordering) o;

        if (employee != null ? !employee.equals(ordering.employee) : ordering.employee != null) return false;
        if (table != null ? !table.equals(ordering.table) : ordering.table != null) return false;
        if (date != null ? !date.equals(ordering.date) : ordering.date != null) return false;
        if (dishList != null ? !dishList.equals(ordering.dishList) : ordering.dishList != null) return false;
        return orderState != null ? orderState.equals(ordering.orderState) : ordering.orderState == null;

    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dishList != null ? dishList.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", employee=" + employee +
                ", table=" + table +
                ", date=" + date +
                ", dishList=" + dishList.toString() +
                ", orderState=" + orderState +
                '}';
    }
}