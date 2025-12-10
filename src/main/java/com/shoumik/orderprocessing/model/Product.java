package com.shoumik.orderprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean active;
    private Integer quantity;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
