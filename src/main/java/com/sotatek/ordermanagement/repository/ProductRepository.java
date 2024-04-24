package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    Product findById(long productId);

    List<Product> findAllByName(String name);
    List<Product> findAllByPrice(double price);
    List<Product> findAllByNameAndPrice(String name, double price);
    List<Product> findAll();
}
