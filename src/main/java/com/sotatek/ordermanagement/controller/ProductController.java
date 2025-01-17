package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.SortType;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping()
    public List<ProductDetailsResponse> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) SortType sortPriceType) {
        return productServiceImpl.getProducts(name, price, sortPriceType);
    }

    @GetMapping("/{productId}")
    public ProductDetailsResponse getProductDetails(@PathVariable("productId") long productId) {
        return productServiceImpl.getProductDetailsById(productId);
    }

    @PostMapping("create")
    @SecurityRequirement(name = "bearerAuth")
    @Secured({"ADMIN", "OPERATOR"})
    public ProductDetailsResponse createProduct(@Valid @RequestBody CreateProductRequest request) {
        return productServiceImpl.createProduct(request);
    }

    @PatchMapping("/update/{productId}")
    @SecurityRequirement(name = "bearerAuth")
    @Secured("ADMIN")
    public ProductDetailsResponse updateProduct(
            @PathVariable("productId") long productId,
            @Valid @RequestBody UpdateProductRequest request) {
        return productServiceImpl.updateProduct(productId, request);
    }

    @DeleteMapping("{productId}")
    @SecurityRequirement(name = "bearerAuth")
    @Secured("ADMIN")
    public ProductDetailsResponse deleteProduct(@PathVariable("productId") long productId) {
        return productServiceImpl.deleteProduct(productId);
    }
}
