package com.demo.testcontainersdemo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.demo.testcontainersdemo.entity.User;
import com.demo.testcontainersdemo.service.api.UserService;

@Testcontainers
@SpringBootTest
class UserServiceImplTest {

    @Container
    private static PostgreSQLContainer database =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:12").withReuse(true);
    @Autowired
    UserService userService = new UserServiceImpl();

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
        propertyRegistry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Test
    void createUser() {
        System.out.println(database.getJdbcUrl());

        User user = new User();
        user.setAge(46);
        user.setName("test-demo");
        User user1 = userService.createUser(user);
        assertNotNull(user1);
        // int userCount = userService.retrieveUser().size();
        // assertEquals(1, userCount);
    }

    @Test
    void retrieveUser() {
        List<User> users = List.of(new User("test-1", 2), new User("test-2", 9));
        List<User> userList = userService.createUserList(users);
        assertEquals(users, userList);

        int userCount = userService.retrieveUser().size();
        assertEquals(2, userCount);
    }

}