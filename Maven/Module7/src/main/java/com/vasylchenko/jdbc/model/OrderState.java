package com.vasylchenko.jdbc.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "orderstate")
public class OrderState {
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
        return "OrderState{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
