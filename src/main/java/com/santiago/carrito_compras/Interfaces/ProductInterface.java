package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Dto.ProductDto;
import com.santiago.carrito_compras.Entities.Product;

import java.util.List;

public interface ProductInterface{
    Product createProduct(ProductDto product);
    Product updateProductById(ProductDto product, long id);
    void deleteProductById(long id);
    List<ProductDto> searchAllProducts();

    Product findProductById(long id);

    boolean existById(long id);

    ProductDto findProductInfoById(long id);


}