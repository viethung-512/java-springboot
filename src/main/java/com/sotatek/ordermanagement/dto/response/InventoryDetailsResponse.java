package com.sotatek.ordermanagement.dto.response;

import com.sotatek.ordermanagement.entity.Inventory;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class InventoryDetailsResponse {
    long id;
    long stockQuantity;
    Date updatedDate;
    ProductDetailsResponse product;

    public static InventoryDetailsResponse from(Inventory inventory) {
        return InventoryDetailsResponse.builder()
                .id(inventory.getId())
                .stockQuantity(inventory.getStockQuantity())
                .updatedDate(inventory.getUpdatedDate())
                .product(ProductDetailsResponse.from(inventory.getProduct()))
                .build();
    }
}
