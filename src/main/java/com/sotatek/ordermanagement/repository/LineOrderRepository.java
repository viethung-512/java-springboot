package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.LineOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineOrderRepository extends JpaRepository<LineOrder, Long> {
    List<LineOrder> findAllByOrderId(long orderId);
}
