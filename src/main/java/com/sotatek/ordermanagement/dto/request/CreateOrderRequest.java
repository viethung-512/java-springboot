package com.sotatek.ordermanagement.dto.request;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOrderRequest {
    List<CreateLineOrderRequest> lineOrders;
    @NotEmpty
    long customerId;
}
