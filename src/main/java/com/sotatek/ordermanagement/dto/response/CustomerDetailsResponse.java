package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.Customer;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerDetailsResponse {
    Long id;
    String name;
    String phone;
    String address;

    public static CustomerDetailsResponse from(final Customer customer) {
        return CustomerDetailsResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }
}
