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

    public static ProductDetailsResponse from(final Product product) {
        return ProductDetailsResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getInventory().getStockQuantity())
                .build();
    }
}
