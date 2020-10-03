package com.order.demo.service;

import com.order.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService{
    User getUser(Long id);

    User getUser(String email);

    User createUser(User user);

    List<User> getUsers();

    User editUser(User user);

    boolean deleteUserById(Long id);

    boolean deleteAll();

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean findByPassword(String email, String password);
}
