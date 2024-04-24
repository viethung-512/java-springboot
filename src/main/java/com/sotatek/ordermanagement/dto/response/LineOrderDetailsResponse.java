package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.LineOrder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LineOrderDetailsResponse {
    long quantity;
    long customerId;
    long productId;

    public static LineOrderDetailsResponse from(LineOrder lineOrder) {
        return LineOrderDetailsResponse.builder()
                .quantity(lineOrder.getQuantity())
                .customerId(lineOrder.getCustomerId())
                .productId(lineOrder.getProductId())
                .build();
    }
}
