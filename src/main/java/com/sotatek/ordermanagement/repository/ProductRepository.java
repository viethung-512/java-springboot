package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
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
                    "SELECT NEW"
                        + " com.sotatek.ordermanagement.dto.response.ProductDetailsResponse(P.id,"
                        + " P.name, P.price, I.stockQuantity) FROM Product P JOIN Inventory I ON"
                        + " P.id = I.productId WHERE ( :name IS NULL OR P.name LIKE CONCAT('%',"
                        + " :name, '%'))AND (:price IS NULL OR P.price = :price)")
    List<ProductDetailsResponse> findAllWithCondition(String name, Double price);

    @Query(
            value =
                    "SELECT NEW"
                        + " com.sotatek.ordermanagement.dto.response.ProductDetailsResponse(p.id,"
                        + " p.name, p.price, i.stockQuantity) FROM Product p JOIN Inventory i ON"
                        + " p.id = i.productId WHERE p.id = :productId")
    ProductDetailsResponse findProductDetailsById(long productId);

    @Query(
            value =
                    "SELECT NEW"
                        + " com.sotatek.ordermanagement.dto.response.ProductDetailsResponse(P.id,"
                        + " P.name, P.price, I.stockQuantity) FROM Product P JOIN Inventory I ON"
                        + " P.id = I.productId WHERE I.stockQuantity <= :qty")
    List<ProductDetailsResponse> findAllProductHasQtyLessThan(long qty);
}
