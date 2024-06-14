package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.ProductAmount;

public interface ProductAmountInterface {

    public ProductAmount createProductAmount(long cartId, long productId, int amount);
}
