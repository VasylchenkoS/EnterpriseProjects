package com.vasilchenko.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_menu")
    private int id;

    @Column(name = "name")
    private String menuName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_dish",
            joinColumns = @JoinColumn(name = "id_menu"),
            inverseJoinColumns = @JoinColumn(name = "id_dish"))
    private Set<Dish> dishSet = new LinkedHashSet<>();

    public Menu() {
    }

    public Menu(String menuName, Set<Dish> dishSet) {
        this.menuName = menuName;
        this.dishSet = dishSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishList) {
        this.dishSet = dishList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", dishList=" + dishSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (menuName != null ? !menuName.equals(menu.menuName) : menu.menuName != null) return false;
        return dishSet != null ? dishSet.equals(menu.dishSet) : menu.dishSet == null;

    }

    @Override
    public int hashCode() {
        int result = menuName != null ? menuName.hashCode() : 0;
        result = 31 * result + (dishSet != null ? dishSet.hashCode() : 0);
        return result;
    }
}
