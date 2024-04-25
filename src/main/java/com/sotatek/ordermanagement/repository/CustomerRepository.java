package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    Customer findByPhone(String phone);

    Customer findById(long id);

    @Query(
            value =
                    "SELECT C from Customer C WHERE (:name IS NULL OR C.name LIKE CONCAT('%',"
                        + " :name, '%'))AND (:phone IS NULL OR C.phone = :phone)AND (:address IS"
                        + " NULL OR C.address LIKE CONCAT('%', :address, '%') )")
    List<Customer> findAllWithCondition(String name, String phone, String address);
}
