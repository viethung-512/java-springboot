package com.sotatek.ordermanagement.service;

import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;

import java.util.List;

public interface ReportService {
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3();
    public Double getTotalRevenue(String from, String to);
    public CustomerDetailsResponse getMostPotentialCustomer();
}
