package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ProductAmount {
    @EmbeddedId
    ProductAmountKey id;

    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shoppingCart_id")
    ShoppingCart shoppingCart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @Getter
    @Setter
    int amount;

}
