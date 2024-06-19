package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Dto.ShoppingCartDto;
import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Entities.ProductAmount;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Interfaces.ShoppingCartInterface;
import com.santiago.carrito_compras.Repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartService implements ShoppingCartInterface {

    private final ShoppingCartRepository repository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductAmountService productAmountService;

    public ShoppingCartService(ShoppingCartRepository repository, UserService userService, ProductService productService, ProductAmountService productAmountService) {
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
        this.productAmountService = productAmountService;
    }

    @Override
    public ShoppingCartDto createShoppingCart(long userId) {
        LocalDateTime date = LocalDateTime.now();
        Set<User> user = new HashSet<>();
        User userData = userService.findUserById(userId);
        user.add(userData);
        ShoppingCart shoppingCartUser = new ShoppingCart(date.toString(), "inProgress", user);
        repository.save(shoppingCartUser);
        userData.addCart(shoppingCartUser);
        userService.updateUser(userData);
        return shoppingCartUser.getDto();
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        if (repository.existsById(shoppingCart.getId())){
            return repository.save(shoppingCart);
        }
        return null;
    }

    @Override
    public void deleteShoppingCart(long id, long userId) {
        ShoppingCart cart = repository.findById(id).orElse(null);
        User user = userService.findUserById(userId);
        Set<ShoppingCart> userCarts = user.getCarts();
        userCarts.remove(cart);
        user.setCarts(userCarts);
        cart.setUser(null);
        userService.updateUser(user);
        repository.deleteById(id);
    }

    @Override
    public ProductAmount addProduct(long cartId, long proId, int amount) {
        return productAmountService.createProductAmount(cartId,proId,amount);
    }


    @Override
    public ShoppingCart findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean existById(long id) {
        return repository.existsById(id);
    }


}
