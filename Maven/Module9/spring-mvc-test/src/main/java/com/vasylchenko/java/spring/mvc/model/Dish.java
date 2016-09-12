package com.vasylchenko.java.spring.mvc.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_dish")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "price")
    private double price;

    @Column(name = "weigth")
    private double weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dishingredients",
    joinColumns = @JoinColumn(name = "id_dish"),
    inverseJoinColumns = @JoinColumn(name = "id_ingridient"))
    private Set<Storage> ingredients = new LinkedHashSet<>();

    public Dish() {
    }

    public Dish(String name, Category category, double price, double weight) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
    }

    public Dish(String name, Category category, double price, double weight, Set<Storage> ingredients) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.ingredients = ingredients;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Storage> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Storage> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (Double.compare(dish.price, price) != 0) return false;
        if (Double.compare(dish.weight, weight) != 0) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        return category != null ? category.equals(dish.category) : dish.category == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
