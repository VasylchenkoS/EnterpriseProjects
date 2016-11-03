package com.vasilchenko.java.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(generator = "increments")
    @GenericGenerator(name = "increments", strategy = "increment")
    @Column(name = "id_ingridient")
    private int id;

    @Column(name = "ingredient")
    private String ingredientName;

    @Column(name = "quantity")
    private long quantity;

    public Storage() {
    }

    public Storage(String ingredientName, long quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;

        Storage storage = (Storage) o;

        if (quantity != storage.quantity) return false;
        return ingredientName != null ? ingredientName.equals(storage.ingredientName) : storage.ingredientName == null;

    }

    @Override
    public int hashCode() {
        int result = ingredientName != null ? ingredientName.hashCode() : 0;
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }
}