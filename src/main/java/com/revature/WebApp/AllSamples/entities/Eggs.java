package com.revature.WebApp.AllSamples.entities;

import javax.persistence.*;

@Entity
public class Eggs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @ManyToOne(targetEntity = Baskets.class)
    Baskets basket;

    public Eggs() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasket(Baskets basket) {
        this.basket = basket;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Baskets getBasket() {
        return basket;
    }
}
