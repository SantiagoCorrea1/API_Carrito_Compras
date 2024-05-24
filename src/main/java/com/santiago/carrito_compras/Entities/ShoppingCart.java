package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity

public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "shoppingCarts")
    private List<Product> products;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
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
