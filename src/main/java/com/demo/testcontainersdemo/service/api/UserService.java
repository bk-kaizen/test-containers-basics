package com.demo.testcontainersdemo.service.api;

import com.demo.testcontainersdemo.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> retrieveUser();

    List<User> createUserList(List<User> users);
}
