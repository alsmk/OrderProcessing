package com.shoumik.orderprocessing.controller;

import com.shoumik.orderprocessing.dto.OrderMessage;
import com.shoumik.orderprocessing.service.MessageProducerService;
import com.shoumik.orderprocessing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
    private final MessageProducerService messageProducerService;
    @PostMapping
    public ResponseEntity<Map<String,String>> placeOrder(@RequestBody OrderMessage orderRequest){
        String orderId = "ORD-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
        orderRequest.setOrderId(orderId);
        orderRequest.setOrderStatus("PENDING");
        orderRequest.setOrderDate(LocalDateTime.now());
        messageProducerService.sendOrderPlacement(orderRequest);
        Map<String,String > response = new HashMap<>();
        response.put("message", "Order placed successfully");
        response.put("orderId", orderId);
        response.put("status", "Order is being processed");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> placeTestOrder(){
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrderId("ORD-TEST-"+System.currentTimeMillis());
        orderMessage.setCustomerId("CUST-001");
        orderMessage.setCustomerMail("customer@example.com");
        orderMessage.setOrderStatus("PENDING");
        orderMessage.setOrderDate(LocalDateTime.now());
        OrderMessage.OrderItem item1 = new OrderMessage.OrderItem(
                        "product-id-1",
                        "Test Product",
                        new BigDecimal("979877"),
                        12
                );
        orderMessage.setItems(List.of(item1));
        orderMessage.setAmount(new BigDecimal("455657"));

        messageProducerService.sendOrderPlacement(orderMessage);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Test order placed successfully");
        response.put("orderId", orderMessage.getOrderId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

}
