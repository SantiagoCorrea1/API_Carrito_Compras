package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ProductAmount;
import com.santiago.carrito_compras.Entities.ProductAmountKey;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import com.santiago.carrito_compras.Interfaces.ProductAmountInterface;
import com.santiago.carrito_compras.Repositories.ProductAmountRepository;
import com.santiago.carrito_compras.Repositories.ProductRepository;
import com.santiago.carrito_compras.Repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductAmountService implements ProductAmountInterface {
    private final ProductAmountRepository productAmountRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ProductAmountService(ProductAmountRepository productAmountRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository) {
        this.productAmountRepository = productAmountRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }


    @Override
    public ProductAmount createProductAmount(long shoppingCartId, long productId, int amount) {
        // Buscar entidades ShoppingCart y Product
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new IllegalArgumentException("ShoppingCart not found: " + shoppingCartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        // Crear clave compuesta
        ProductAmountKey id = new ProductAmountKey(shoppingCartId, productId);

        // Crear nueva instancia de ProductAmount
        ProductAmount productAmount = new ProductAmount();
        productAmount.setId(id);
        productAmount.setShoppingCart(shoppingCart);
        productAmount.setProduct(product);
        productAmount.setAmount(amount);

        // Persistir la nueva relación
        return productAmountRepository.save(productAmount);
    }
}
