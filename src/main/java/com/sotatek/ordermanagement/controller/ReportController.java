package com.sotatek.ordermanagement.controller;

import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.InventoryService;
import com.sotatek.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportController {
    private final InventoryService inventoryService;
    private final OrderService orderService;

    @GetMapping("products-qty-less-or-equal-than-3")
    @Secured({"ADMIN", "OPERATOR"})
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3() {
        return inventoryService.getListProductQtyLessOrEqualThan3();
    }

    @GetMapping("total-revenue")
    @Secured({"ADMIN", "OPERATOR"})
    public Double getTotalRevenue(@RequestParam() String from, @RequestParam() String to) {
        return orderService.getTotalRevenue(from, to);
    }
}
