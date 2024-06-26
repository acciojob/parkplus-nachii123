package com.driver.services.impl;

import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository4;
    @Override
    public void deleteUser(Integer userId) {

//        Optional<User> userOptional = userRepository4.findById(userId);
        userRepository4.deleteById(userId);

    }

    @Override
    public User updatePassword(Integer userId, String password) {
        Optional<User> user = userRepository4.findById(userId);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setPassword(password);
            return userRepository4.save(user1);
        }

        return null;

    }

    @Override
    public void register(String name, String phoneNumber, String password) {

        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        userRepository4.save(user);

    }
}
