package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Dto.ProductDto;
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
    public List<ProductDto> searchAllProducts() {
        return service.searchAllProducts();
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<?> searchProduct(@PathVariable long id) {
        if (!service.existById(id)){
            return new ResponseEntity<>("Product not found by id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findProductInfoById(id), HttpStatus.OK);
    }

    @PostMapping ("/products/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDto updatedProduct) {
        if (!service.existById(id)){
            return new ResponseEntity<>("Product not found by id " + id, HttpStatus.NOT_FOUND);
        }
        Product product = service.updateProductById(updatedProduct, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("/products/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        if (!service.existById(id)){
            return new ResponseEntity<>("Product not found by id " + id, HttpStatus.NOT_FOUND);
        }
        service.deleteProductById(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @PostMapping("/products/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDao){
        return new ResponseEntity<>(service.createProduct(productDao), HttpStatus.CREATED);
    }
}
