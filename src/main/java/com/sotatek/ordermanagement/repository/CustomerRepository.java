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

    List<Customer> findAllByName(String name);

    List<Customer> findAllByPhone(String name);

    List<Customer> findAllByAddress(String name);

    List<Customer> findAllByNameAndPhone(String name, String phone);

    List<Customer> findAllByNameAndAddress(String name, String address);

    List<Customer> findAllByPhoneAndAddress(String name, String address);

    List<Customer> findAllByNameAndPhoneAndAddress(String name, String phone, String address);
}
