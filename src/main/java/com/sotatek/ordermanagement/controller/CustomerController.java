package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateCustomerRequest;
import com.sotatek.ordermanagement.dto.response.CustomerDetailsResponse;
import com.sotatek.ordermanagement.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    @GetMapping("")
    @Secured({"ADMIN", "OPERATOR"})
    public List<CustomerDetailsResponse> filterCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address) {
        return customerServiceImpl.getCustomerWithConditions(name, phone, address);
    }

    @GetMapping("/{customerId}")
    @Secured({"ADMIN", "OPERATOR"})
    public CustomerDetailsResponse getCustomerDetailsById(
            @PathVariable("customerId") long customerId) {
        return customerServiceImpl.getCustomerDetailsById(customerId);
    }

    @PostMapping("create")
    @Secured({"ADMIN", "OPERATOR"})
    public CustomerDetailsResponse createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {
        return customerServiceImpl.createCustomer(request);
    }

    @DeleteMapping("{customerId}")
    @Secured("ADMIN")
    public CustomerDetailsResponse deleteCustomer(@PathVariable("customerId") long customerId) {
        return customerServiceImpl.deleteCustomer(customerId);
    }
}
