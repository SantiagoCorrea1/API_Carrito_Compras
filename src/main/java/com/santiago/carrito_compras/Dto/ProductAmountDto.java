package com.santiago.carrito_compras.Dto;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ProductAmountKey;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import lombok.Data;

@Data
public class ProductAmountDto {

    ProductAmountKey id;
    long shoppingCartId;
    long productId;
    int amount;
}
