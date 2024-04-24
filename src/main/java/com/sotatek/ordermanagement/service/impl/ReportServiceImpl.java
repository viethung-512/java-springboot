package com.sotatek.ordermanagement.service.impl;

import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final InventoryServiceImpl inventoryServiceImpl;
    private final OrderServiceImpl orderServiceImpl;

    @Override
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3() {
        return inventoryServiceImpl.getListProductQtyLessOrEqualThan3();
    }

    @Override
    public Double getTotalRevenue(String from, String to) {
        return orderServiceImpl.getTotalRevenue(from, to);
    }

    @Override
    public CustomerDetailsResponse getMostPotentialCustomer() {
        return null;
    }
}
