package com.santiago.carrito_compras.Dto;

import com.santiago.carrito_compras.Entities.ProductAmount;
import com.santiago.carrito_compras.Entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class ShoppingCartDao {

    private long id;

    private String createdAt;

    private String status;

    private Set<Long> user;

    //Set<ProductAmount> amount;

}
