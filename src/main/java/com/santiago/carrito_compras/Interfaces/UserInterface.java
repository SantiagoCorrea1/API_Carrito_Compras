package com.santiago.carrito_compras.Interfaces;

import com.santiago.carrito_compras.Entities.User;

public interface UserInterface {
    User createUser(User user);

    User updateUser(User user);

    void deleteUser(long id);

    boolean existById(long id);

}
