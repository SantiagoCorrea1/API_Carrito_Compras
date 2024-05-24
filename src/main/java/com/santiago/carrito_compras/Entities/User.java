package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;
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
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;


    public User(String name, String password, Rol rol, ShoppingCart shoppingCart) {
        this.name = name;
        this.password = password;
        this.rol = rol;
        this.shoppingCart = shoppingCart;
    }

    public User() {
    }
}
