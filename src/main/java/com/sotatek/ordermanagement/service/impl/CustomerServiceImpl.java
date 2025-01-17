package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.dto.request.CreateCustomerRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.entity.Customer;
import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.entity.Order;
import com.sotatek.ordermanagement.exception.CustomerPhoneExistsException;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.repository.CustomerRepository;
import com.sotatek.ordermanagement.repository.LineOrderRepository;
import com.sotatek.ordermanagement.repository.OrderRepository;
import com.sotatek.ordermanagement.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final LineOrderRepository lineOrderRepository;

    public List<CustomerDetailsResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDetailsResponse::from).toList();
    }

    public List<CustomerDetailsResponse> getCustomerWithConditions(
            String name, String phone, String address) {
        final List<Customer> customers =
                customerRepository.findAllWithCondition(name, phone, address);
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
        final Customer customer = getCustomerById(customerId);
        customerRepository.deleteById(customerId);
        return CustomerDetailsResponse.from(customer);
    }

    public Customer getCustomerById(long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public CustomerDetailsResponse getCustomerDetailsById(long customerId) {
        final Customer customer = getCustomerById(customerId);
        final List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        final List<OrderDetailsResponse> orderDetailsResponses =
                orders.stream()
                        .map(
                                order -> {
                                    final List<LineOrder> lineOrders =
                                            lineOrderRepository.findAllByOrderId(order.getId());
                                    return OrderDetailsResponse.from(order, lineOrders);
                                })
                        .toList();

        return CustomerDetailsResponse.from(customer, orderDetailsResponses);
    }

    public boolean isPhoneNumberExists(String phone) {
        return customerRepository.findByPhone(phone) != null;
    }
}
