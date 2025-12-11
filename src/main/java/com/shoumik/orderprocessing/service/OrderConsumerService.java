package com.shoumik.orderprocessing.service;

import com.shoumik.orderprocessing.dto.InventoryUpdateMessage;
import com.shoumik.orderprocessing.dto.NotificationMessage;
import com.shoumik.orderprocessing.dto.OrderMessage;
import jakarta.jms.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderConsumerService {
    private final MessageProducerService messageProducerService;
    @JmsListener(destination = "${queue.order.placement}")
    public void processOrderPlacement(OrderMessage orderMessage) {
        log.info("Received order placement message from queue {}", orderMessage.getOrderId());
        try{
            Thread.sleep(1000);
            log.info("Processing order; {}", orderMessage.getOrderId());
            for(OrderMessage.OrderItem item : orderMessage.getItems()){
                InventoryUpdateMessage inventoryUpdateMessage = new InventoryUpdateMessage(
                        item.getProductId(),
                        -item.getQuantity(),
                        orderMessage.getOrderId(),
                        "DEDUCT",
                        LocalDateTime.now()
                );
                messageProducerService.sendInventoryUpdate(inventoryUpdateMessage);
            }

            NotificationMessage notificationMessage = new NotificationMessage(
                    orderMessage.getOrderId(),
                    orderMessage.getCustomerId(),
                    orderMessage.getCustomerMail(),
                    "EMAIL",
                    "Order Confirmation",
                    "Your order " + orderMessage.getOrderId() +  "Has been placed",
                    LocalDateTime.now()
            );
            messageProducerService.sendOrderNotification(notificationMessage);
            orderMessage.setOrderStatus("Processing");
            messageProducerService.sendOrderStatusUpdate(orderMessage);

        } catch(Exception e){
            log.error("Error sending inventory update message to queue {}", orderMessage.getOrderId(), e);
        }
    }
    @JmsListener(destination = "${queue.order.status-update}")
    public void processOrderStatusUpdate(OrderMessage orderMessage) {
        log.info("Received order status update message from queue {}", orderMessage.getOrderId());

    }
}
