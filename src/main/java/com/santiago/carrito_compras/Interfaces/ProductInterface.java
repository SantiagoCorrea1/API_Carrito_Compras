package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ShoppingCart;

import java.util.List;

public interface ProductInterface{
    Product createProduct(Product product);
    Product updateProductById(Product product, long id);
    void deleteProductById(long id);
    List<Product> searchAllProducts();

    Product findProductById(Product product);



}
