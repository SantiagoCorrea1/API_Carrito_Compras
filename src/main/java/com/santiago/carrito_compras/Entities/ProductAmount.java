package com.santiago.carrito_compras.Entities;

import com.santiago.carrito_compras.Dto.ProductAmountDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
public class ProductAmount implements Serializable {
    @Getter
    @Setter
    @EmbeddedId
    private ProductAmountKey id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter
    @Setter
    private int amount;

    public ProductAmount() {
    }
    public ProductAmount(ProductAmountKey id, ShoppingCart shoppingCart, Product product, int amount) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.amount = amount;
    }

    public ProductAmountDto getDto(){
        ProductAmountDto dto = new ProductAmountDto();
        dto.setProductId(product.getId());
        dto.setAmount(amount);
        dto.setId(id);
        dto.setShoppingCartId(shoppingCart.getId());
        return dto;
    }

}
