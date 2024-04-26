package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);


    @Query(
            value =
                    "SELECT P from Product P "
                            + "WHERE ( :name IS NULL OR P.name LIKE CONCAT('%', :name, '%'))"
                            + "AND (:price IS NULL OR P.price = :price)")
    List<Product> findAllWithCondition(String name, Double price);

    List<Product> findAll();
}
