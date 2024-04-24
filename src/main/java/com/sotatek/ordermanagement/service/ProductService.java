package com.sotatek.ordermanagement.service;

import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductDetailsResponse> getProducts(String name, Double price, Boolean sortPriceByDesc);
    public ProductDetailsResponse createProduct(CreateProductRequest request);
    public ProductDetailsResponse updateProduct(long productId, UpdateProductRequest request);
    public ProductDetailsResponse deleteProduct(long productId);
    public Product getProductByIdOrFail(long productId);
    public boolean isProductNameExists(String productName);
}
