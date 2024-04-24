package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.LineOrder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LineOrderDetailsResponse {
    long quantity;
    CustomerDetailsResponse customer;
    ProductDetailsResponse product;

    public static LineOrderDetailsResponse from(LineOrder lineOrder) {
        return LineOrderDetailsResponse.builder()
                .customer(CustomerDetailsResponse.from(lineOrder.getCustomer()))
                .product(ProductDetailsResponse.from(lineOrder.getProduct()))
                .build();
    }
}
