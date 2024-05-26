package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Dto.ProductDao;
import com.santiago.carrito_compras.Dto.UserDto;
import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Services.ProductService;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/products/{id}")
    public Product searchProduct(@PathVariable long id) {
        return service.findProductById(id);
    }

    @PostMapping ("/products/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDao updatedProduct) {
        Product product = service.updateProductById(updatedProduct, id);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        service.deleteProductById(id);
    }

    @PostMapping("/products/create")
    public Product createProduct(@RequestBody ProductDao productDao){
        return service.createProduct(productDao);
    }
}
