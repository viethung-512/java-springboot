package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3();

    public Double getTotalRevenue(LocalDateTime from, LocalDateTime to);

    public CustomerDetailsResponse getMostPotentialCustomer();
}
