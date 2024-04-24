package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.repository.LineOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineOrderService {
    private final LineOrderRepository lineOrderRepository;
}
