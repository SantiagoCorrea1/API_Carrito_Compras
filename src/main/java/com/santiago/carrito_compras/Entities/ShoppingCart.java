package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String createdAt;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "carts")
    private Set<User> user;

    @OneToMany(mappedBy = "shoppingCart")
    Set<ProductAmount> amount;


    public ShoppingCart() {
    }

    public ShoppingCart(String createdAt, String status) {
        this.createdAt = createdAt;
        this.status = status;
    }
}
