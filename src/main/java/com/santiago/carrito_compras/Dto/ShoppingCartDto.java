package com.santiago.carrito_compras.Dto;

import lombok.Data;

import java.util.Set;

@Data
public class ShoppingCartDto {

    private long id;

    private String createdAt;

    private String status;

    private long userId;

    //Set<ProductAmount> amount;

}
