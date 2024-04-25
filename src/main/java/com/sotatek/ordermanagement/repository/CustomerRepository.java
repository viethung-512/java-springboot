package com.sotatek.ordermanagement.repository;


import com.sotatek.ordermanagement.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    Customer findByPhone(String phone);

    Customer findById(long id);

    List<Customer> findAllByNameLikeIgnoreCase(String name);

    List<Customer> findAllByPhone(String name);

    List<Customer> findAllByAddressLikeIgnoreCase(String name);
    List<Customer> findAllByNameLikeIgnoreCaseAndPhone(String name, String phone);

    List<Customer> findAllByNameLikeIgnoreCaseAndAddressLikeIgnoreCase(String name, String address);

    List<Customer> findAllByPhoneAndAddressLikeIgnoreCase(String name, String address);

    List<Customer> findAllByNameLikeIgnoreCaseAndPhoneAndAddressLikeIgnoreCase(String name, String phone, String address);
}
