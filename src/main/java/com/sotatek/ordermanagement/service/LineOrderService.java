package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.repository.LineOrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineOrderService {
    private final LineOrderRepository lineOrderRepository;

    public List<LineOrder> savedLineOrders(List<LineOrder> lineOrders) {
        return lineOrderRepository.saveAll(lineOrders);
    }
}
