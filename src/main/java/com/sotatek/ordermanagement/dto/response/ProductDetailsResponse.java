package com.sotatek.ordermanagement.dto.response;

import com.sotatek.ordermanagement.entity.Customer;
import com.sotatek.ordermanagement.entity.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDetailsResponse {
    Long id;
    String name;
    Double price;

    public static ProductDetailsResponse from(final Product product) {
        return ProductDetailsResponse.builder().id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
