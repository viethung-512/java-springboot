package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Product;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.ProductNameExistsException;
import com.sotatek.ordermanagement.repository.ProductRepository;
import com.sotatek.ordermanagement.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final InventoryServiceImpl inventoryServiceImpl;
    private final ProductRepository productRepository;

    public List<ProductDetailsResponse> getProducts(
            String name, Double price, Boolean sortPriceByDesc) {
        List<Product> products;
        if (name != null && price != null) {
            products = productRepository.findAllByNameAndPrice(name, price);
        } else if (name != null) {
            products = productRepository.findAllByName(name);
        } else if (price != null) {
            products = productRepository.findAllByPrice(price);
        } else {
            products = productRepository.findAll();
        }

        boolean isSortedPriceDesc = sortPriceByDesc != null;
        if (isSortedPriceDesc) {

            products =
                    products.stream()
                            .sorted((a, b) -> (int) (a.getPrice() - b.getPrice()))
                            .toList();
        } else {

            products =
                    products.stream()
                            .sorted((a, b) -> (int) (a.getPrice() - b.getPrice()))
                            .toList();
        }

        return products.stream().map(ProductDetailsResponse::from).toList();
    }

    @Transactional
    public ProductDetailsResponse createProduct(CreateProductRequest request) {
        if (isProductNameExists(request.getName())) {
            throw new ProductNameExistsException(request.getName());
        }
        final Product product =
                Product.builder().name(request.getName()).price(request.getPrice()).build();
        final Product savedProduct = productRepository.save(product);
        inventoryServiceImpl.initInventory(product.getId());
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
        final Product product = getProductByIdOrFail(productId);
        productRepository.deleteById(productId);
        return ProductDetailsResponse.from(product);
    }

    public Product getProductByIdOrFail(long productId) {
        final Product product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        return product;
    }

    public boolean isProductNameExists(String productName) {
        return productRepository.findByName(productName) != null;
    }
}
