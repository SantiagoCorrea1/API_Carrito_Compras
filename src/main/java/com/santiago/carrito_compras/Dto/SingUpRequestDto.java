package com.santiago.carrito_compras.Dto;

import lombok.Data;

@Data
public class SingUpRequestDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private long rol;
}
