package com.santiago.carrito_compras.Entities;

import com.santiago.carrito_compras.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    @Getter
    @Setter
    private  Rol rol;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingCart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;


    public User(String name, String email, String password, Rol rol, ShoppingCart shoppingCart) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.shoppingCart = shoppingCart;
    }

    public User() {
    }

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setRol(rol);
        userDto.setShoppingCart(shoppingCart);
        return userDto;
    }
}
