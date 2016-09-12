package com.vasylchenko.java.spring.mvc.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "kitchendishstate")
public class KitchenDishState {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_state")
    private int id;

    @Column(name = "state")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "KitchenDishState{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KitchenDishState)) return false;

        KitchenDishState that = (KitchenDishState) o;

        return state != null ? state.equals(that.state) : that.state == null;

    }

    @Override
    public int hashCode() {
        return state != null ? state.hashCode() : 0;
    }
}

