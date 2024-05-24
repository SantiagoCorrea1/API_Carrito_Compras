package com.santiago.carrito_compras.Dto;

import com.santiago.carrito_compras.Entities.Rol;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import lombok.Data;

@Data
public class SingUpRequestDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private long rol;
    private long shoppingCart;
}
