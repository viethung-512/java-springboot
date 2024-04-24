package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Product;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.ProductNameExistsException;
import com.sotatek.ordermanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDetailsResponse createProduct(CreateProductRequest request) {
        if (isProductNameExists(request.getName())) {
            throw new ProductNameExistsException(request.getName());
        }
        final Product product =
                Product.builder().name(request.getName()).price(request.getPrice()).build();
        final Product savedProduct = productRepository.save(product);
        return ProductDetailsResponse.from(savedProduct);
    }

    public ProductDetailsResponse updateProduct(long productId, UpdateProductRequest request) {
        final Product product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        product.setPrice(request.getPrice());
        final Product savedProduct = productRepository.save(product);
        return ProductDetailsResponse.from(savedProduct);
    }

    public ProductDetailsResponse deleteProduct(long productId) {
        final Product product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        productRepository.deleteById(productId);
        return ProductDetailsResponse.from(product);
    }

    public ProductDetailsResponse getProductDetails(long productId) {
        final Product product = productRepository.findById(productId);
        return ProductDetailsResponse.from(product);
    }

    public boolean isProductExists(long productId) {
        return productRepository.findById(productId) != null;
    }

    public boolean isProductNameExists(String productName) {
        return productRepository.findByName(productName) != null;
    }
}
