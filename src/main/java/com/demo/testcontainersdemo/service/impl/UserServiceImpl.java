package com.demo.testcontainersdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.testcontainersdemo.entity.User;
import com.demo.testcontainersdemo.repo.UserRepository;
import com.demo.testcontainersdemo.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> retrieveUser() {
        return userRepository.findAll();
    }
}
