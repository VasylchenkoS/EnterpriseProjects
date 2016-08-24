package com.vasylchenko.jdbc.model;


import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private Date birth;
    private String phone;
    private String position;
    private float salary;

    public Employee(int id, String name, String surname, Date birth, String phone, String position, float salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name, String surname, Date birth, String phone, String position, float salary) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
