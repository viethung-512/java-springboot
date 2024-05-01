package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.impl.ReportServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final ReportServiceImpl reportServiceImpl;

    @GetMapping("products-qty-less-or-equal-than-3")
    @Secured({"ADMIN", "OPERATOR"})
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3() {
        return reportServiceImpl.getListProductQtyLessOrEqualThan3();
    }

    @GetMapping("total-revenue")
    @Secured({"ADMIN", "OPERATOR"})
    public Double getTotalRevenue(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return reportServiceImpl.getTotalRevenue(from, to);
    }

    @GetMapping("most-potential-customer")
    @Secured({"ADMIN", "OPERATOR"})
    public CustomerDetailsResponse getMostPotentialCustomer() {
        return reportServiceImpl.getMostPotentialCustomer();
    }
}
