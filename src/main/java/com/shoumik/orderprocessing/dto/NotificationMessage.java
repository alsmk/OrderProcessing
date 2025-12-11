package com.shoumik.orderprocessing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NotificationMessage implements Serializable {
    private String orderId;
    private String customerId;
    private String customerMail;
    private String notificationType;
    private String subject;
    private String message;
    private LocalDateTime timestamp;
}
