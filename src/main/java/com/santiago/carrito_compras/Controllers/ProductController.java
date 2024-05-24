package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> searchAllProducts() {
        return service.searchAllProducts();
    }

    @PostMapping ("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = service.updateProductById(updatedProduct, id);
        return ResponseEntity.ok(product);
    }
}
