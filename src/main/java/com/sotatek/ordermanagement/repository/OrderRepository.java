package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long orderId);

    List<Order> findAllByIssueDateBetween(LocalDate from, LocalDate to);
}
