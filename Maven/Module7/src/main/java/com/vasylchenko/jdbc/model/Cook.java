package com.vasylchenko.jdbc.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cook extends Employee {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    @Fetch(FetchMode.SELECT)
    private List<Kitchen> uncoockedDishes;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee")
    @Fetch(FetchMode.SELECT)
    private List<KitchenHistory> coockedDishes;

    public List<KitchenHistory> getCoockedDishes() {
        return coockedDishes;
    }

    public void setCoockedDishes(List<KitchenHistory> orders) {
        this.coockedDishes = orders;
    }

    public List<Kitchen> getUncoockedDishes() {
        return uncoockedDishes;
    }

    public void setUncoockedDishes(List<Kitchen> uncoockedDishes) {
        this.uncoockedDishes = uncoockedDishes;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "uncoockedDishes=" + uncoockedDishes +
                ", coockedDishes=" + coockedDishes +
                '}';
    }
}