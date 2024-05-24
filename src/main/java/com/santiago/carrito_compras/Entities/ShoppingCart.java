package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity

public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @ManyToMany
    @JoinTable(
            name = "shopping_cart_product",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Getter
    @Setter
    private List<Product> products;


    @OneToOne(mappedBy = "shoppingCart")
    @Getter
    @Setter
    private User user;

    public ShoppingCart(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }
    public ShoppingCart() {

    }
}
