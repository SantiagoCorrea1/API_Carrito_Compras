package com.santiago.carrito_compras.Entities;


import com.santiago.carrito_compras.Dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String img;
    @Getter
    @Setter
    private int availableAmount;
    @Getter
    @Setter
    private boolean available;

    @Getter
    @Setter
    @OneToMany(mappedBy = "product")
    Set<ProductAmount> amount;

    public Product() {
    }

    public Product(double price, String name, String description, String img, int availableAmount, boolean available) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.img = img;
        this.availableAmount = availableAmount;
        this.available = available;
    }
    public ProductDto getDto(){
        ProductDto dto= new ProductDto();
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setImg(img);
        dto.setName(name);
        dto.setAvailable(available);
        dto.setAvailableAmount(availableAmount);
        return dto;
    }
}
