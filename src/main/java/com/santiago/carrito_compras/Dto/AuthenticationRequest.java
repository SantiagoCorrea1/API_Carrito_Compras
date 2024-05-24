package com.santiago.carrito_compras.Dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String name;
    private String password;
}
