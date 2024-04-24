package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final ProductService productService;

    @GetMapping()
    public List<ProductDetailsResponse> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Boolean sortPriceByDesc) {
        return productService.getProducts(name, price, sortPriceByDesc);
    }

    @PostMapping("create")
    @SecurityRequirement(name = "bearerAuth")
    @Secured({"ADMIN", "OPERATOR"})
    public ProductDetailsResponse createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/update/{productId}")
    @SecurityRequirement(name = "bearerAuth")
    @Secured("ADMIN")
    public ProductDetailsResponse updateProduct(
            @PathVariable("productId") long productId, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("{productId}")
    @SecurityRequirement(name = "bearerAuth")
    @Secured("ADMIN")
    public ProductDetailsResponse deleteProduct(@PathVariable("productId") long productId) {
        return productService.deleteProduct(productId);
    }
}
