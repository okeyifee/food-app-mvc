package com.order.demo.service;

import com.order.demo.dto.UserResponse;
import com.order.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService{

    User getUser(Long id);

    User getUser(String email);

    User createUser(User user);

    /**
     * method declaration that returns a list of users
     *
     * @return <List<UserResponse>>
     */
    List<UserResponse> getUsers();

    User editUser(User user);


    /**
     * method declaration that deletes user using user id
     *
     * @return true
     */
    boolean deleteUserById(Long id);


    boolean deleteAll();

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean findByPassword(String email, String password);

    User findById(Long id);
}
