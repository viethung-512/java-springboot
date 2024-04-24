package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @PostMapping("create")
    @Secured({"ADMIN", "OPERATOR"})
    public OrderDetailsResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderServiceImpl.createOrder(request);
    }
}
