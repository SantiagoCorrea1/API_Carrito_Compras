package com.santiago.carrito_compras.Entities;

import com.santiago.carrito_compras.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
public class User {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany
    @JoinTable(
            name = "user_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shoppingCart_id"))
    private Set<ShoppingCart> carts;
    public User(String name, String email, String password, Rol rol, ShoppingCart shoppingCart) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
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
        return userDto;
    }
}