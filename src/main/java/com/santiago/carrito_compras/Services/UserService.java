package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Entities.User;
import com.santiago.carrito_compras.Interfaces.UserInterface;
import com.santiago.carrito_compras.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public User findUserById(long id) {
        User user = repository.findById(id).orElse(null);
        user.setRol(null);
        return user;
    }
}
