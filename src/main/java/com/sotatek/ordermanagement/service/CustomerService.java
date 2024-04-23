package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateCustomerRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.entity.Customer;
import com.sotatek.ordermanagement.exception.CustomerPhoneExistsException;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.repository.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<CustomerDetailsResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDetailsResponse::from).toList();
    }

    public List<CustomerDetailsResponse> getCustomerWithConditions(
            String name, String phone, String address) {
        List<Customer> customers;
        if (name != null && phone != null && address != null) {
            customers = customerRepository.findAllByNameAndPhoneAndAddress(name, phone, address);
        } else if (name != null && phone != null) {
            customers = customerRepository.findAllByNameAndPhone(name, phone);
        } else if (name != null && address != null) {
            customers = customerRepository.findAllByNameAndAddress(name, address);
        } else if (phone != null && address != null) {
            customers = customerRepository.findAllByPhoneAndAddress(phone, address);
        } else if (name != null) {
            customers = customerRepository.findAllByName(name);
        } else if (phone != null) {
            customers = customerRepository.findAllByPhone(phone);
        } else if (address != null) {
            customers = customerRepository.findAllByAddress(address);
        } else {
            customers = customerRepository.findAll();
        }
        return customers.stream().map(CustomerDetailsResponse::from).toList();
    }

    public CustomerDetailsResponse createCustomer(CreateCustomerRequest request) {
        if (isPhoneNumberExists(request.getPhone())) {
            throw new CustomerPhoneExistsException(request.getPhone());
        }
        final Customer customer =
                Customer.builder()
                        .name(request.getName())
                        .phone(request.getPhone())
                        .address(request.getAddress())
                        .build();
        final Customer savedCustomer = customerRepository.save(customer);
        return CustomerDetailsResponse.from(savedCustomer);
    }

    public CustomerDetailsResponse deleteCustomer(long customerId) {
        final Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }
        customerRepository.deleteById(customerId);
        return CustomerDetailsResponse.from(customer);
    }

    private boolean isPhoneNumberExists(String phone) {
        return customerRepository.findByPhone(phone) != null;
    }
}
