package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(long productId);
    List<Inventory> findAllByStockQuantityLessThanEqual(int number);
}
