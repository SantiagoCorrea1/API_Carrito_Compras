package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


}
