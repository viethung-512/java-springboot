package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(long userId);

    List<User> findAll();
}
