package com.vasylchenko.java.spring.mvc.model;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "kitchen")
@DiscriminatorOptions(force = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Kitchen {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_kitchen")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    private Employee cook;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private Ordering order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dish")
    private Dish dishName;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dishstate")
    private KitchenDishState status;

    public Kitchen() {
    }

    public Kitchen(Employee cook, Ordering order, KitchenDishState status, Date date, Dish dishName) {
        this.cook = cook;
        this.order = order;
        this.status = status;
        this.date = date;
        this.dishName = dishName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Employee cook) {
        this.cook = cook;
    }

    public Ordering getOrder() {
        return order;
    }

    public void setOrder(Ordering order) {
        this.order = order;
    }

    public KitchenDishState getStatus() {
        return status;
    }

    public void setStatus(KitchenDishState status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish getDishName() {
        return dishName;
    }

    public void setDishName(Dish dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "id=" + id +
                ", cook=" + cook +
                ", order=" + order +
                ", status=" + status +
                ", date=" + date +
                ", dishName=" + dishName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kitchen)) return false;

        Kitchen kitchen = (Kitchen) o;

        if (cook != null ? !cook.equals(kitchen.cook) : kitchen.cook != null) return false;
        if (order != null ? !order.equals(kitchen.order) : kitchen.order != null) return false;
        if (status != null ? !status.equals(kitchen.status) : kitchen.status != null) return false;
        if (date != null ? !date.equals(kitchen.date) : kitchen.date != null) return false;
        return dishName != null ? dishName.equals(kitchen.dishName) : kitchen.dishName == null;

    }

    @Override
    public int hashCode() {
        int result = cook != null ? cook.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        return result;
    }
}
