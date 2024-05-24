package com.santiago.carrito_compras.Controllers;

import com.santiago.carrito_compras.Dto.AuthenticationRequest;
import com.santiago.carrito_compras.Dto.SingUpRequestDto;
import com.santiago.carrito_compras.Dto.UserDto;
import com.santiago.carrito_compras.Services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user/sing-up")
    public ResponseEntity<?> singUpUser(@RequestBody SingUpRequestDto singUpRequestDto){
        if(authService.presentByEmail(singUpRequestDto.getEmail())){
            return new ResponseEntity<>("User already exits with this email", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.SingUpClient(singUpRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

//    @PostMapping
//    public void createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest,
//                                           HttpServletResponse response){
//
//    }
}
