package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Dto.ProductDto;
import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Services.ProductService;
import com.santiago.carrito_compras.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;
    private final UserService userService;

    public ProductController(ProductService service, UserService userService) {
        this.service = service;
        this.userService = userService;
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

    @PostMapping ("/admin/products/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDto updatedProduct) {
        if (!service.existById(id)){
            return new ResponseEntity<>("Product not found by id " + id, HttpStatus.NOT_FOUND);
        }
        String username = userService.getUserId();
        if(userService.isAdmin(userService.findUserByEmail(username))){
            Product product = service.updateProductById(updatedProduct, id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Forbidden", HttpStatus.NOT_ACCEPTABLE);

    }
    @PostMapping("/admin/products/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        if (!service.existById(id)){
            return new ResponseEntity<>("Product not found by id " + id, HttpStatus.NOT_FOUND);
        }
        String username = userService.getUserId();
        if(userService.isAdmin(userService.findUserByEmail(username))){
            service.deleteProductById(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Forbidden", HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/admin/products/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDao){
        String username = userService.getUserId();
        if(userService.isAdmin(userService.findUserByEmail(username))){
            return new ResponseEntity<>(service.createProduct(productDao), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Forbidden", HttpStatus.NOT_ACCEPTABLE);
    }
}
