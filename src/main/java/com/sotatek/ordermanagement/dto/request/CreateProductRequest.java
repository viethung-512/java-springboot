package com.sotatek.ordermanagement.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateProductRequest {
    @NotEmpty
    String name;
    @Min(0)
    Double price;
}
