package com.sotatek.ordermanagement.service;

import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.entity.Order;

public interface OrderService {
    public OrderDetailsResponse createOrder(CreateOrderRequest request);
    public Double getTotalRevenue(String from, String to);
    public Order getOrderByIdOrFail(long orderId);
    public CustomerDetailsResponse getMostPotentialCustomer();
}
