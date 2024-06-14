package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Dto.ShoppingCartDto;
import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ProductAmount;
import com.santiago.carrito_compras.Entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartInterface{

    ShoppingCartDto createShoppingCart(long userId);
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
    void deleteShoppingCart(long id, long userId);

    ProductAmount addProduct(long cartId, long proId, int amount);

    ShoppingCart deleProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart findById(long id);

    boolean existById(long id);

}
