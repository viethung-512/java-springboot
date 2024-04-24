package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIssueDateBetween(String from, String to);
}
