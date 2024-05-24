package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Dto.SingUpRequestDto;
import com.santiago.carrito_compras.Dto.UserDto;

public interface AuthInterface {

    UserDto SingUpClient(SingUpRequestDto singUpRequestDto);
    Boolean presentByEmail(String email);
}
