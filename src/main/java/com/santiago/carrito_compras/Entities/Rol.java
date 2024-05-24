package com.santiago.carrito_compras.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String rol;

    @OneToMany(mappedBy = "rol")
    private List<User> users;

    public Rol(String rol, List<User> users) {
        this.rol = rol;
        this.users = users;
    }
    public Rol() {
    }
}
