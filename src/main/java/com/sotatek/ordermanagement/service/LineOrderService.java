package com.sotatek.ordermanagement.service;

import com.sotatek.ordermanagement.entity.LineOrder;

import java.util.List;

public interface LineOrderService {
    public List<LineOrder> savedLineOrders(List<LineOrder> lineOrders);
}
