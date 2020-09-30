package com.order.demo.serviceImpl;


import com.order.demo.model.User;
import com.order.demo.repository.UserRepository;
import com.order.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return false;
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean deleteAll() {
        log.info("Deleting all restaurants ... ");
        userRepository.deleteAll();
        return true;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean findByPassword(String email, String password) {
        User user = userRepository.findUserByEmail(email).get();
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
