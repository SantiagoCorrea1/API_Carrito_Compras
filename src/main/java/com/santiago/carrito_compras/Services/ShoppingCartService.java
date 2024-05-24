package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import com.santiago.carrito_compras.Interfaces.ShoppingCartInterface;
import com.santiago.carrito_compras.Repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements ShoppingCartInterface {

    private final ShoppingCartRepository repository;

    public ShoppingCartService(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        if (repository.existsById(shoppingCart.getId())){
            return repository.save(shoppingCart);
        }
        return null;
    }

    @Override
    public void deleteShoppingCart(long id) {
        repository.deleteById(id);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart == null || product == null) {
            throw new IllegalArgumentException("ShoppingCart and Product cannot be null");
        }
        List<Product> products = shoppingCart.getProducts();
        products.add(product);
        shoppingCart.setProducts(products);
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart == null || product == null) {
            throw new IllegalArgumentException("ShoppingCart and Product cannot be null");
        }
        List<Product> products = shoppingCart.getProducts();
        products.remove(product);
        shoppingCart.setProducts(products);
        return repository.save(shoppingCart);
    }


}
