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

import java.util.Set;

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

    @Override
    public void deleteProductAmount(long cartId, long proId, int deleteAmount) {
        ProductAmount productAmount = productAmountRepository.findByProductIdAndShoppingCartId(proId, cartId);
        if (productAmount.getAmount() == deleteAmount){
            productAmountRepository.delete(productAmount);
        } else {
            productAmount.setAmount(productAmount.getAmount() - deleteAmount);
            productAmountRepository.save(productAmount);
        }
    }
    @Override
    public ProductAmount getProductAmount(Long productId, Long shoppingCartId) {
        return productAmountRepository.findByProductIdAndShoppingCartId(productId, shoppingCartId);
    }

    @Override
    public boolean existProductAmount(Long productId, Long shoppingCartId) {
        return productAmountRepository.existsProductAmountByProductIdAndShoppingCartId(productId, shoppingCartId);
    }

    @Override
    public ProductAmount updateProductAmount(long cartId, long productId, int amount) {
        ProductAmount productAmount = getProductAmount(productId, cartId);
        productAmount.setAmount(productAmount.getAmount() + amount);
        return productAmountRepository.save(productAmount);
    }
}
