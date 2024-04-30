package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDetailsResponse {
    Long id;
    String name;
    Double price;
    Long stockQuantity;
}
