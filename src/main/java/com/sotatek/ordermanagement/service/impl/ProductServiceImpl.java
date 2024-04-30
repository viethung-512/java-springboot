package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.dto.request.CreateProductRequest;
import com.sotatek.ordermanagement.dto.request.SortType;
import com.sotatek.ordermanagement.dto.request.UpdateProductRequest;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Inventory;
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
            String name, Double price, SortType sortPriceType) {
        List<ProductDetailsResponse> productsWithDetails = productRepository.findAllWithCondition(name, price);

        if (sortPriceType == SortType.DESC) {

            productsWithDetails =
                    productsWithDetails.stream()
                            .sorted((a, b) -> (int) (b.getPrice() - a.getPrice()))
                            .toList();
        } else if (sortPriceType == SortType.ASC) {

            productsWithDetails =
                    productsWithDetails.stream()
                            .sorted((a, b) -> (int) (a.getPrice() - b.getPrice()))
                            .toList();
        }

        return productsWithDetails;
    }

    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3() {
        return productRepository.findAllProductHasQtyLessThan(3);
    }

    @Transactional
    public ProductDetailsResponse createProduct(CreateProductRequest request) {
        if (isProductNameExists(request.getName())) {
            throw new ProductNameExistsException(request.getName());
        }
        final Product product =
                Product.builder().name(request.getName()).price(request.getPrice()).build();
        final Product savedProduct = productRepository.save(product);
        inventoryServiceImpl.initInventory(savedProduct.getId());
        return productRepository.findProductDetailsById(savedProduct.getId());
    }

    public ProductDetailsResponse updateProduct(long productId, UpdateProductRequest request) {
        final Product product = getProductById(productId);
        product.setPrice(request.getPrice());
        final Product savedProduct = productRepository.save(product);
        return productRepository.findProductDetailsById(savedProduct.getId());
    }

    public ProductDetailsResponse deleteProduct(long productId) {
        final ProductDetailsResponse prDetails = getProductDetailsById(productId);
        productRepository.deleteById(productId);
        return prDetails;
    }

    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public ProductDetailsResponse getProductDetailsById(long productId) {
        final ProductDetailsResponse prDetails = productRepository.findProductDetailsById(productId);
        if (prDetails == null) {
            throw new NotFoundException("Product not found");
        }
        return prDetails;
    }

    public boolean isProductNameExists(String productName) {
        return productRepository.findByName(productName) != null;
    }
}
