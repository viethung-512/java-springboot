package com.sotatek.ordermanagement.dto.request;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CreateOrderRequest {
    List<CreateLineOrderRequest> lineOrders;
    long customerId;
}
