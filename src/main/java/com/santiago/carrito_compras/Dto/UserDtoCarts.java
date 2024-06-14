package com.santiago.carrito_compras.Dto;

import com.santiago.carrito_compras.Entities.Rol;
import lombok.Data;

import java.util.Set;

@Data
public class UserDtoCarts {
        private long id;
        private String name;
        private String email;
        private String password;
        private Rol rol;
        private Set<Long> cartIds;
}
