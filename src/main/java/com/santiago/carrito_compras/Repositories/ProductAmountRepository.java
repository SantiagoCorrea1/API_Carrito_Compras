package com.santiago.carrito_compras.Repositories;

import com.santiago.carrito_compras.Entities.ProductAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAmountRepository extends JpaRepository<ProductAmount, Long> {
}
