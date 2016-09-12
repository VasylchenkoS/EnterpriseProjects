package com.vasylchenko.java.spring.mvc.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Employee {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    @Fetch(FetchMode.SELECT)
    private List<Ordering> orders;

    public List<Ordering> getOrders() {
        return orders;
    }

    public void setOrders(List<Ordering> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter {\n");
        sb.append("     ID = ").append(getId()).append("\n");
        sb.append("     Name = ").append(getName()).append("\n");
        sb.append("     Surname = ").append(getSurname()).append("\n");
        sb.append("     Order = {   ");
        orders.forEach(order -> sb.append(order).append("\n"));
        sb.append("     }\n");
        sb.append("}\n");
        return sb.toString();
    }
}