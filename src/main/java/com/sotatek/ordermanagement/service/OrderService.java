package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.entity.Order;

import java.time.Instant;
import java.time.LocalDateTime;

public interface OrderService {
    public OrderDetailsResponse createOrder(CreateOrderRequest request);

    public Double getTotalRevenue(LocalDateTime from, LocalDateTime to);

    public Order getOrderById(long orderId);

    public CustomerDetailsResponse getMostPotentialCustomer();
}
