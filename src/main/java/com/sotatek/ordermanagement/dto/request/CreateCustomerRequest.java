package com.sotatek.ordermanagement.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomerRequest {
    @NotEmpty String name;
    @NotEmpty String phone;
    @NotEmpty String address;
}
