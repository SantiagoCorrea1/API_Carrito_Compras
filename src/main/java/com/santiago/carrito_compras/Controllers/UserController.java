package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/shopping-cart")
    public User cartProducts(@PathVariable long id){
        return userService.findUserById(id);
    }
}
