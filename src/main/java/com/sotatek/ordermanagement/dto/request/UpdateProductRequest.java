package com.sotatek.ordermanagement.dto.request;


import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductRequest {
    @Min(0)
    Double price;
}
