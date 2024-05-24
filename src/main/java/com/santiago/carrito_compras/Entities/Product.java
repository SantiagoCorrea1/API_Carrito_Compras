package com.santiago.carrito_compras.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Getter
    @Setter
    private int availableAmount;

    @Getter
    @Setter
    private boolean available;

    @ManyToMany(mappedBy = "products")
    @Getter
    @Setter
    private List<ShoppingCart> shoppingCarts;


    public Product() {
    }

    public Product(double price, String name, String description, String img, int availableAmount, boolean available) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.img = img;
        this.availableAmount = availableAmount;
        this.available = available;
    }
}
