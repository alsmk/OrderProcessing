package com.shoumik.orderprocessing.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message="Product name is required")
    @Size(min=3, max= 100)
    private String name;

    @Size(min=3, max=100)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1",message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity can not be negative")
    private Integer quantity;

    @NotBlank(message = "Category is required")
    private String category;



}
