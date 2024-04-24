package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateCustomerRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.entity.Customer;
import java.util.List;

public interface CustomerService {
    public List<CustomerDetailsResponse> getCustomers();

    public List<CustomerDetailsResponse> getCustomerWithConditions(
            String name, String phone, String address);

    public CustomerDetailsResponse createCustomer(CreateCustomerRequest request);

    public CustomerDetailsResponse deleteCustomer(long customerId);

    public Customer getCustomerByIdOrFail(long customerId);

    public boolean isPhoneNumberExists(String phone);
}
