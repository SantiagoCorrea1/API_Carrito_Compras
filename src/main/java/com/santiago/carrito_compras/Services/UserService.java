package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Dto.UserDtoCarts;
import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Interfaces.UserInterface;
import com.santiago.carrito_compras.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterface {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (repository.existsById(user.getId())){
            return repository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(long id) {
        return repository.existsById(id);
    }

    public User findUserById(long id) {
        User user = repository.findById(id).orElse(null);
        return user;
    }

    public UserDtoCarts getUserInfo(long id){
        return repository.findById(id).get().getDtoCarts();
    }

    public long findUserByEmail(String email){
        User user = repository.findFirstByEmail(email);
        return user.getId();
    }

    public String getUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            return userDetails.getUsername();
        } else {
            return auth.getPrincipal().toString();
        }
    }

    public boolean isAdmin(long id){
        long rol = findUserById(id).getRol().getId();
        if (rol == 1){
            return true;
        }
        return false;
    }
}
