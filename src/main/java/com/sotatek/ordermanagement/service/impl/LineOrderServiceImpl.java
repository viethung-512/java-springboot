package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.repository.LineOrderRepository;
import com.sotatek.ordermanagement.service.LineOrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineOrderServiceImpl implements LineOrderService {
    private final LineOrderRepository lineOrderRepository;

    public List<LineOrder> savedLineOrders(List<LineOrder> lineOrders) {
        return lineOrderRepository.saveAll(lineOrders);
    }

    public List<LineOrder> getLineOrdersByOrderId(long orderId) {
        return lineOrderRepository.findAllByOrderId(orderId);
    }
}
