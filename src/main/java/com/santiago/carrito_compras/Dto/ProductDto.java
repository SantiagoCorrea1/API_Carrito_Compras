package com.santiago.carrito_compras.Dto;

import lombok.Data;

@Data
public class ProductDao {

    private double price;
    private String name;
    private String description;
    private String img;
    private int availableAmount;
    private boolean available;
}
