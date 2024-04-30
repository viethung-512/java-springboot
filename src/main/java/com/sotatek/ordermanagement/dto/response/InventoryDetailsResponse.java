package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.Inventory;
import java.util.Date;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InventoryDetailsResponse {
    long id;
    long stockQuantity;
    Date updatedDate;

    public static InventoryDetailsResponse from(Inventory inventory) {
        return InventoryDetailsResponse.builder()
                .id(inventory.getId())
                .stockQuantity(inventory.getStockQuantity())
                .updatedDate(inventory.getUpdatedDate())
                .build();
    }
}
