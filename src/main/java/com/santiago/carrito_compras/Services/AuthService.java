package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Dto.SingUpRequestDto;
import com.santiago.carrito_compras.Dto.UserDto;
import com.santiago.carrito_compras.Entities.Rol;
import com.santiago.carrito_compras.Entities.ShoppingCart;
import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Interfaces.AuthInterface;
import com.santiago.carrito_compras.Repositories.RolRepository;
import com.santiago.carrito_compras.Repositories.ShoppingCartRepository;
import com.santiago.carrito_compras.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    public AuthService(UserRepository userRepository, RolRepository rolRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public UserDto SingUpClient(SingUpRequestDto singUpRequestDto){

        Rol rol = rolRepository.findById(singUpRequestDto.getRol())
                .orElseThrow(() -> new RuntimeException("Rol not found"));
        ShoppingCart shoppingCart = shoppingCartRepository.findById(singUpRequestDto.getShoppingCart())
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));


        User user = new User();
        user.setName(singUpRequestDto.getName());
        user.setEmail(singUpRequestDto.getEmail());
        user.setPassword(singUpRequestDto.getPassword());
        user.setRol(rol);
        user.setShoppingCart(shoppingCart);

        return userRepository.save(user).getDto();
    }

    public  Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }


}
