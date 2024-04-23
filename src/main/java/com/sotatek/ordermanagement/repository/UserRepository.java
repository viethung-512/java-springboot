package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(long userId);
    List<User> findAll();
}
