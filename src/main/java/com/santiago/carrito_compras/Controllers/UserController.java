package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Services.ShoppingCartService;
import com.santiago.carrito_compras.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public UserController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

//    @PostMapping("/user")
//    public ResponseEntity<?> getUserById(@PathVariable long id){
//        if (!userService.existById(id)){
//            return new ResponseEntity<>("User not found by id" + id, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(userService.getUserInfo(id), HttpStatus.OK);
//    }



    @PostMapping("/user/create-cart")
    public ResponseEntity<?> createCart(){
        String username = userService.getUserId();
        long userId = userService.findUserByEmail(username);
        return  new ResponseEntity<>(shoppingCartService.createShoppingCart(userId), HttpStatus.OK);
    }

    @PostMapping("/user/delete-cart-{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable long cartId){
        if (!shoppingCartService.existById(cartId)){
            return new ResponseEntity<>("Cart not found by id " + cartId, HttpStatus.NOT_FOUND);
        }
        String username = userService.getUserId();
        long userId = userService.findUserByEmail(username);
        shoppingCartService.deleteShoppingCart(cartId, userId);
        return  new ResponseEntity<>("Cart deleted correctly", HttpStatus.OK);
    }
}
