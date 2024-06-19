package com.santiago.carrito_compras.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ProductAmountKey implements Serializable {
    @Getter
    @Setter
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    @Getter
    @Setter
    @Column(name = "product_id")
    private Long productId;

    public ProductAmountKey(Long shoppingCartId, Long productId) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
    }
    public ProductAmountKey() {
    }

}
