package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.response.LineOrderDetailsResponse;
import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.repository.LineOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineOrderService {
    private final LineOrderRepository lineOrderRepository;

    public List<LineOrderDetailsResponse> savedLineOrders(List<LineOrder> lineOrders) {
        final List<LineOrder> savedLineOrders = lineOrderRepository.saveAll(lineOrders);
        return savedLineOrders.stream().map(LineOrderDetailsResponse::from).toList();
    }
}
