package com.sotatek.ordermanagement.dto.request;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class FilterCustomerRequest {
    String name;
    String phone;
    String address;
}
