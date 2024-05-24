package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ShoppingCart;

import java.util.List;

public interface ShoppingCartInterface{

    ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
    void deleteShoppingCart(long id);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart deleProduct(ShoppingCart shoppingCart, Product product);

}
