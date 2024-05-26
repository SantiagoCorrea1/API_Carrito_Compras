package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Dto.ProductDao;
import com.santiago.carrito_compras.Entities.Product;

import java.util.List;

public interface ProductInterface{
    Product createProduct(ProductDao product);
    Product updateProductById(ProductDao product, long id);
    void deleteProductById(long id);
    List<Product> searchAllProducts();

    Product findProductById(long id);



}