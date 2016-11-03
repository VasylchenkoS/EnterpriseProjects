package com.vasilchenko.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name =
        "tablesit")
public class Tables {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_table")
    private int id;

    @Column(name = "number")
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
