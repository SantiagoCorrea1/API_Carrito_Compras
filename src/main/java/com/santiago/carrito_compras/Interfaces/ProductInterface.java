package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.Product;

import java.util.List;

public interface ProductInterface{
    Product createProduct(Product product);
    Product updateProductById(Product product);
    void deleteProductById(long id);
    List<Product> searchAllProducts();

}
