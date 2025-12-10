package com.shoumik.orderprocessing.controller;

import com.shoumik.orderprocessing.dto.ProductRequest;
import com.shoumik.orderprocessing.model.Product;
import com.shoumik.orderprocessing.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product=productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());

    }
    @PutMapping("/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(id, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategory(category));
    }

}
