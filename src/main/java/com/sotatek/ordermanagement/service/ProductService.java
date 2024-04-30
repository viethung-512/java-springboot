package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.SortType;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Product;
import java.util.List;

public interface ProductService {
    public List<ProductDetailsResponse> getProducts(
            String name, Double price, SortType sortPriceType);

    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3();

    public ProductDetailsResponse createProduct(CreateProductRequest request);

    public ProductDetailsResponse updateProduct(long productId, UpdateProductRequest request);

    public ProductDetailsResponse deleteProduct(long productId);

    public Product getProductById(long productId);

    public boolean isProductNameExists(String productName);
}
