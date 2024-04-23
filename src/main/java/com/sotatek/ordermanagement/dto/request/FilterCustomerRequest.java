package com.sotatek.ordermanagement.dto.request;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FilterCustomerRequest {
    String name;
    String phone;
    String address;
}
