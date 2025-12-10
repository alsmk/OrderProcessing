package com.shoumik.orderprocessing.repository;

import com.shoumik.orderprocessing.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findByActiveTrue();
    List<Product>findByNameContainingIgnoreCase(String name);

}
