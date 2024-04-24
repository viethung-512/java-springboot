package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.response.LineOrderDetailsResponse;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Customer;
import com.sotatek.ordermanagement.entity.Inventory;
import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.entity.Order;
import com.sotatek.ordermanagement.entity.Product;
import com.sotatek.ordermanagement.exception.DateStringIsNotCorrectException;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.ProductQuantityIsNotEnoughException;
import com.sotatek.ordermanagement.repository.LineOrderRepository;
import com.sotatek.ordermanagement.repository.OrderRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final LineOrderService lineOrderService;

    private final OrderRepository orderRepository;

    public OrderDetailsResponse createOrder(CreateOrderRequest request) {
        final Customer customer = customerService.getCustomerByIdOrFail(request.getCustomerId());
        final Order order = Order.builder()
                .totalMoney((double) 0)
                .issueDate(new Date())
                .build();
        final Order savedOrder = orderRepository.save(order);

        final List<LineOrder> lineOrders = request.getLineOrders().stream().map(createLineOrderRequest -> {
            final Product product = productService.getProductByIdOrFail(createLineOrderRequest.getProductId());
            final Inventory inventory = inventoryService.getInventoryByProductIdOrFailed(createLineOrderRequest.getProductId());
            if (inventory.getStockQuantity() < createLineOrderRequest.getQuantity()) {
                throw new ProductQuantityIsNotEnoughException(inventory.getProduct().getName());
            }
            return LineOrder.builder()
                    .customer(customer)
                    .product(product)
                    .order(savedOrder)
                    .build();
        }).toList();
        final List<LineOrderDetailsResponse> savedLineOrders = lineOrderService.savedLineOrders(lineOrders);

        Double totalMoney = lineOrders.stream()
                        .mapToDouble(lineOrder -> lineOrder.getProduct().getPrice() * lineOrder.getQuantity())
                        .sum();
        order.setTotalMoney(totalMoney);
        order.setIssueDate(new Date());
        final Order updatedOrder = orderRepository.save(order);
        return OrderDetailsResponse.from(updatedOrder);
    }

    public Double getTotalRevenue(String from, String to) {
        if (!GenericValidator.isDate(from, Locale.ROOT) || !GenericValidator.isDate(to, Locale.ROOT)) {
            throw new DateStringIsNotCorrectException();
        }
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        final List<Order> orders = orderRepository.findAllByIssueDateBetween(fromDate, toDate);
        return orders.stream().mapToDouble(Order::getTotalMoney).sum();
    }
}
