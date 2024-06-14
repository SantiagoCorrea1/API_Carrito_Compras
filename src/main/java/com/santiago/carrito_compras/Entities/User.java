package com.santiago.carrito_compras.Entities;

import com.santiago.carrito_compras.Dto.UserDto;
import com.santiago.carrito_compras.Dto.UserDtoCarts;
import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@ToString
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

    public User(String name, String email, String password, Rol rol) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public User(Set<ShoppingCart> carts) {
        this.carts = carts;
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

    public UserDtoCarts getDtoCarts(){
        UserDtoCarts userDto = new UserDtoCarts();
        Set<Long> cartIds = new HashSet<>();
        for (ShoppingCart cart: carts) {
            cartIds.add(cart.getId());
        }
        userDto.setName(name);
        userDto.setId(id);
        userDto.setRol(rol);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setCartIds(cartIds);
        return userDto;
    }

    public void addCart(ShoppingCart cart){
        carts.add(cart);
    }
}