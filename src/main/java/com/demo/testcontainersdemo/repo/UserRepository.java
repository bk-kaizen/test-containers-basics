package com.demo.testcontainersdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.testcontainersdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
