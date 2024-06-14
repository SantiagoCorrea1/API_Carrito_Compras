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
    ProductAmountKey id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shoppingCart_id")
    ShoppingCart shoppingCart;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @Getter
    @Setter
    int amount;

    public ProductAmountDto getDto(){
        ProductAmountDto dto = new ProductAmountDto();
        dto.setProductId(product.getId());
        dto.setAmount(amount);
        dto.setId(id);
        dto.setShoppingCartId(shoppingCart.getId());
        return dto;
    }

}
