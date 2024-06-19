package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.ProductAmount;

public interface ProductAmountInterface {

    public ProductAmount createProductAmount(long cartId, long productId, int amount);
    public void deleteProductAmount(long cartId, long proId, int amount);

    public ProductAmount getProductAmount(Long productId, Long shoppingCartId);
    public boolean existProductAmount(Long productId, Long shoppingCartId);
    public ProductAmount updateProductAmount(long cartId, long productId, int amount);
}
