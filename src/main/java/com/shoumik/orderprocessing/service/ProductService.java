package com.shoumik.orderprocessing.service;

import com.shoumik.orderprocessing.dto.ProductRequest;
import com.shoumik.orderprocessing.model.Product;
import com.shoumik.orderprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public Product createProduct(ProductRequest productRequest) {
        log.info("Creating product {}", productRequest.getName());
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setActive(true);
        product.setDescription(productRequest.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);

    }
    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    public Product getProductById(String productId) {
        log.info("Getting product {}", productId);
        return productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found with id " + productId));

    }

    public Product updateProduct(String productId, ProductRequest productRequest) {
        log.info("Updating product {}", productId);
        Product product= getProductById(productId);
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setUpdatedAt(LocalDateTime.now());
        product.setDescription(productRequest.getDescription());
        return productRepository.save(product);
    }
    public void deleteProduct(String productId) {
        log.info("Deleting product {}", productId);
        productRepository.deleteById(productId);

    }
    public List<Product> getProductsByCategory(String category) {
        log.info("Getting products by category {}", category);
        return productRepository.findByCategory(category);
    }
}
