package com.shoumik.orderprocessing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderMessage implements Serializable {
    private String orderId;
    private String customerId;
    private String customerMail;
    private List<OrderItem> items;
    private String orderStatus;
    private BigDecimal amount;
    private LocalDateTime orderDate;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem implements Serializable {
        private String productId;
        private String productName;
        private BigDecimal price;
        private Integer quantity;

    }
}
