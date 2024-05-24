package com.santiago.carrito_compras.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Setter
    @Getter
    private double price;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Getter
    @Setter
    private String img;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "product_shoppingCarts",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "shoppingCart_id")}
    )
    @Setter
    @Getter
    private List<ShoppingCart> shoppingCarts;


    public Product() {
    }

    public Product(double price, String name, String description, String img) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.img = img;
    }
}
