package com.santiago.carrito_compras.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
public class ProductAmountKey implements Serializable {
    @Getter
    @Setter
    @Column(name = "shoppingCart_id")
    Long shoppingCartId;

    @Getter
    @Setter
    @Column(name = "product_id")
    Long productId;

    public ProductAmountKey(Long shoppingCartId, Long productId) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
    }
    public ProductAmountKey() {
    }
}
