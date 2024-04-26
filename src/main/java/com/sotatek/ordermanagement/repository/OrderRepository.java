package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Order;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "" +
            "SELECT SUM(o.totalMoney) from Order o WHERE o.issueDate >= :from AND o.issueDate <= :to" +
            "")
    Double findTotalRevenueBetween(LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT customer_id FROM bill\n" +
            "WHERE issue_date >= NOW() - INTERVAL 24 HOUR\n" +
            "GROUP BY customer_id\n" +
            "ORDER BY SUM(total_money) DESC\n" +
            "LIMIT 1", nativeQuery = true)
    List<Long> findCustomerOrderHasLargestMoneyInLast24hour();
}
