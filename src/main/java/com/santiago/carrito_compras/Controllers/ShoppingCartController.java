package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Services.ProductService;
import com.santiago.carrito_compras.Services.ShoppingCartService;
import com.santiago.carrito_compras.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService, UserService userService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("user-{userId}/cart-{cartId}/add-product-{proId}-{amount}")
    public ResponseEntity<?> addProductToCart(@PathVariable long cartId, @PathVariable long proId,
                                              @PathVariable long userId, @PathVariable int amount){
        if (!productService.existById(proId)) {
            return new ResponseEntity<>("Product not found by id " + proId, HttpStatus.NOT_FOUND);
        }
        if (!shoppingCartService.existById(cartId)){
            return new ResponseEntity<>("Cart not found by id " + cartId, HttpStatus.NOT_FOUND);
        }
        if (!userService.existById(userId)){
            return new ResponseEntity<>("User not found by id " + userId, HttpStatus.NOT_FOUND);
        }
        shoppingCartService.addProduct(cartId, proId, amount);
        return null;
    }
}
