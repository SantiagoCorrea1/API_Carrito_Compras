package com.santiago.carrito_compras.Entities;

import com.santiago.carrito_compras.Dto.ShoppingCartDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String createdAt;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "carts")
    private Set<User> user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "shoppingCart")
    Set<ProductAmount> amount;


    public ShoppingCart() {
    }

    public ShoppingCart(String createdAt, String status, Set<User> user) {
        this.createdAt = createdAt;
        this.status = status;
        this.user = user;
    }

    public ShoppingCartDto getDto(){
        ShoppingCartDto dto = new ShoppingCartDto();
        User infoUser = user.stream().findFirst().orElse(null);
        dto.setCreatedAt(createdAt);
        dto.setId(id);
        dto.setStatus(status);
        dto.setUserId(infoUser.getId());
        return  dto;
    }
}
