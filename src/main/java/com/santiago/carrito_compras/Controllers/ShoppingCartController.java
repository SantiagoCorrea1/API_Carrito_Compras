package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Services.ProductAmountService;
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
    private final ProductAmountService productAmountService;

    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService, UserService userService, ProductAmountService productAmountService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.productAmountService = productAmountService;
    }

    @PostMapping("user-{userId}/cart-{cartId}/add-product-{proId}-{amount}")
    public ResponseEntity<?> addProductToCart(@PathVariable long cartId, @PathVariable long proId,
                                              @PathVariable long userId, @PathVariable int amount){

        if (!userService.existById(userId)){
            return new ResponseEntity<>("User not found by id " + userId, HttpStatus.NOT_FOUND);
        }
        if (productAmountService.existProductAmount(proId, cartId)){
            productAmountService.updateProductAmount(cartId, proId, amount);
            return new ResponseEntity<>("The amount was updated", HttpStatus.OK);
        }
        if (!productService.existById(proId)) {
            return new ResponseEntity<>("Product not found by id " + proId, HttpStatus.NOT_FOUND);
        }
        if (!shoppingCartService.existById(cartId)){
            return new ResponseEntity<>("Cart not found by id " + cartId, HttpStatus.NOT_FOUND);
        }
        shoppingCartService.addProduct(cartId, proId, amount);
        return new ResponseEntity<>("Product added to cart", HttpStatus.CREATED);
    }

    @PostMapping("user-{userId}/cart-{cartId}/delete-product-{proId}-{amount}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable long cartId, @PathVariable long proId,
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
        productAmountService.deleteProductAmount(proId, cartId, amount);
        return new ResponseEntity<>("Product deleted form cart", HttpStatus.OK);

    }


}
