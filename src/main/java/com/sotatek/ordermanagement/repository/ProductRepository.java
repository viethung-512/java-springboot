package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    Product findById(long productId);

    List<Product> findAllByName(String name);

    List<Product> findAllByPrice(double price);

    List<Product> findAllByNameAndPrice(String name, double price);

    List<Product> findAll();
}
