package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.dto.request.CreateLineOrderRequest;
import com.sotatek.ordermanagement.dto.request.CreateOrderRequest;
import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.OrderDetailsResponse;
import com.sotatek.ordermanagement.entity.Customer;
import com.sotatek.ordermanagement.entity.Inventory;
import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.entity.Order;
import com.sotatek.ordermanagement.entity.Product;
import com.sotatek.ordermanagement.exception.DateStringIsNotCorrectException;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.ProductQuantityIsNotEnoughException;
import com.sotatek.ordermanagement.repository.OrderRepository;
import com.sotatek.ordermanagement.service.OrderService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerServiceImpl customerServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final InventoryServiceImpl inventoryServiceImpl;
    private final LineOrderServiceImpl lineOrderServiceImpl;

    private final OrderRepository orderRepository;

    @Transactional
    public OrderDetailsResponse createOrder(CreateOrderRequest request) {
        final Customer customer =
                customerServiceImpl.getCustomerByIdOrFail(request.getCustomerId());

        // Validate product & quantity & calculate totalMoney
        Double totalMoney =
                request.getLineOrders().stream()
                        .mapToDouble(
                                createLineOrderRequest -> {
                                    final Product product =
                                            productServiceImpl.getProductByIdOrFail(
                                                    createLineOrderRequest.getProductId());
                                    final Inventory inventory =
                                            inventoryServiceImpl.getInventoryByProductIdOrFailed(
                                                    createLineOrderRequest.getProductId());
                                    if (inventory.getStockQuantity()
                                            < createLineOrderRequest.getQuantity()) {
                                        throw new ProductQuantityIsNotEnoughException(
                                                inventory.getProduct().getName());
                                    }
                                    return createLineOrderRequest.getQuantity()
                                            * product.getPrice();
                                })
                        .sum();

        final Order order =
                Order.builder()
                        .customerId(request.getCustomerId())
                        .totalMoney(totalMoney)
                        .issueDate(new Date())
                        .build();
        Order savedOrder = orderRepository.save(order);

        final List<LineOrder> lineOrders = new ArrayList<>();
        for (CreateLineOrderRequest createLineOrderRequest : request.getLineOrders()) {
            LineOrder build =
                    LineOrder.builder()
                            .customerId(customer.getId())
                            .productId(createLineOrderRequest.getProductId())
                            .orderId(savedOrder.getId())
                            .quantity((int) createLineOrderRequest.getQuantity())
                            .build();
            lineOrders.add(build);
        }
        final List<LineOrder> savedLineOrders = lineOrderServiceImpl.savedLineOrders(lineOrders);
        savedOrder.setLineOrders(savedLineOrders);
        savedOrder = orderRepository.save(savedOrder);
        savedLineOrders.forEach(
                lineOrder -> {
                    final Inventory inventory =
                            inventoryServiceImpl.getInventoryByProductIdOrFailed(
                                    lineOrder.getProductId());
                    inventoryServiceImpl.updateInventory(
                            lineOrder.getProductId(),
                            UpdateInventoryRequest.builder()
                                    .stockQuantity(
                                            inventory.getStockQuantity() - lineOrder.getQuantity())
                                    .build());
                });

        return OrderDetailsResponse.from(getOrderByIdOrFail(savedOrder.getId()));
    }

    public Double getTotalRevenue(String from, String to) {
        if (!GenericValidator.isDate(from, Locale.ROOT)
                || !GenericValidator.isDate(to, Locale.ROOT)) {
            throw new DateStringIsNotCorrectException();
        }
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        final List<Order> orders = orderRepository.findAllByIssueDateBetween(fromDate, toDate);
        return orders.stream().mapToDouble(Order::getTotalMoney).sum();
    }

    public Order getOrderByIdOrFail(long orderId) {
        final Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new NotFoundException("Order not found");
        }
        return order;
    }

    public CustomerDetailsResponse getMostPotentialCustomer() {
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = LocalDate.now().minusDays(1);
        final List<Order> orders = orderRepository.findAllByIssueDateBetween(fromDate, toDate);
        HashMap<Long, Double> ordersHashMap = new HashMap<>();

        orders.forEach(
                order -> {
                    Long customerId = order.getCustomerId();
                    ordersHashMap.put(
                            customerId,
                            ordersHashMap.getOrDefault(customerId, 0.0) + order.getTotalMoney());
                });
        if (ordersHashMap.isEmpty()) {
            return null;
        }

        double greatestMoney = Double.MIN_VALUE;
        Long customerId = (long) -1;
        for (Map.Entry<Long, Double> entry : ordersHashMap.entrySet()) {
            Long key = entry.getKey();
            Double value = entry.getValue();
            if (greatestMoney < value) {
                greatestMoney = value;
                customerId = key;
            }
        }
        Customer customer = customerServiceImpl.getCustomerByIdOrFail(customerId);
        return CustomerDetailsResponse.from(customer);
    }
}
