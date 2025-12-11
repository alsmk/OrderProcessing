package com.shoumik.orderprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdateMessage {
    private String productId;
    private Integer quantityChange;
    private String orderId;
    private String operation; // RESERVE, RELEASE, DEDUCT
    private LocalDateTime timestamp;
}
