package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Order;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.repository.OrderRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;

    public OrderDetailsResponse createOrder(CreateOrderRequest request) {
        if (!customerService.isCustomerExists(request.getCustomerId())) {
            throw new NotFoundException("Customer not found");
        }
        Double totalMoney =
                request.getLineOrders().stream()
                        .map(
                                createLineOrderRequest -> {
                                    if (!productService.isProductExists(
                                            createLineOrderRequest.getProductId())) {
                                        throw new NotFoundException(
                                                "Product with id "
                                                        + createLineOrderRequest.getProductId()
                                                        + " not found");
                                    }
                                    inventoryService.reduceStockQuantity(
                                            createLineOrderRequest.getProductId(),
                                            createLineOrderRequest.getQuantity());
                                    ProductDetailsResponse productDetails =
                                            productService.getProductDetails(
                                                    createLineOrderRequest.getProductId());
                                    return productDetails.getPrice()
                                            * createLineOrderRequest.getQuantity();
                                })
                        .mapToDouble(it -> it)
                        .sum();

        final Order order = Order.builder().issueDate(new Date()).totalMoney(totalMoney).build();
        final Order savedOrder = orderRepository.save(order);
        return OrderDetailsResponse.from(savedOrder);
    }
}
