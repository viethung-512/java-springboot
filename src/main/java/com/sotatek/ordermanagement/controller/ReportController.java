package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.impl.InventoryServiceImpl;
import com.sotatek.ordermanagement.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ReportController {
    private final InventoryServiceImpl inventoryServiceImpl;
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping("products-qty-less-or-equal-than-3")
    @Secured({"ADMIN", "OPERATOR"})
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3() {
        return inventoryServiceImpl.getListProductQtyLessOrEqualThan3();
    }

    @GetMapping("total-revenue")
    @Secured({"ADMIN", "OPERATOR"})
    public Double getTotalRevenue(@RequestParam() String from, @RequestParam() String to) {
        return orderServiceImpl.getTotalRevenue(from, to);
    }
}
