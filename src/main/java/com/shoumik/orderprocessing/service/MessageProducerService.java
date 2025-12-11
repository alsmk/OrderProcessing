package com.shoumik.orderprocessing.service;

import com.shoumik.orderprocessing.dto.InventoryUpdateMessage;
import com.shoumik.orderprocessing.dto.NotificationMessage;
import com.shoumik.orderprocessing.dto.OrderMessage;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class MessageProducerService {
    private final JmsTemplate jmsTemplate;
    @Value("${queue.order.placement}")
    private String orderPlacementQueue;

    @Value("${queue.order.notification}")
    private String orderNotificationQueue;

    @Value("${queue.inventory.update}")
    private String inventoryUpdateQueue;

    @Value("${queue.order.status-update}")
    private String orderStatusUpdateQueue;

    public void sendOrderPlacement(OrderMessage orderMessage) {
        log.info("Sending order placement message to queue {}", orderPlacementQueue);
        try{
            jmsTemplate.convertAndSend(orderNotificationQueue,orderMessage);
            log.info("Order placement message sent to queue {}", orderPlacementQueue);
        } catch (Exception e) {
            log.error("Error sending order placement message to queue {}", orderPlacementQueue, e);
            throw new RuntimeException("Error sending order placement message to queue " + orderPlacementQueue, e);
        }
    }

    public void sendOrderNotification(NotificationMessage notificationMessage) {
        log.info("Sending order notification message to queue {}", orderNotificationQueue);
        try{
            jmsTemplate.convertAndSend(orderNotificationQueue,notificationMessage);
            log.info("Order notification message sent to queue {}", orderNotificationQueue);
        } catch (Exception e) {
            log.error("Error sending order notification message to queue {}", orderNotificationQueue, e);
            throw new RuntimeException("Error sending order notification message to queue " + orderNotificationQueue, e);
        }
    }
    public void sendInventoryUpdate(InventoryUpdateMessage inventoryUpdateMessage) {
        log.info("Sending inventory update message to queue {}", inventoryUpdateQueue);
        try{
            jmsTemplate.convertAndSend(inventoryUpdateQueue,inventoryUpdateMessage);
            log.info("Inventory update message sent to queue {}", inventoryUpdateQueue);
        } catch (Exception e) {
            log.error("Error sending inventory update message to queue {}", inventoryUpdateQueue, e);
            throw new RuntimeException("Error sending inventory update message to queue " + inventoryUpdateQueue, e);
        }

    }
    public void sendOrderStatusUpdate (OrderMessage orderMessage){
        log.info("Sending order status update message to queue {}", orderStatusUpdateQueue);
        try{
            jmsTemplate.convertAndSend(orderStatusUpdateQueue,orderMessage);
            log.info("Order status update message sent to queue {}", orderStatusUpdateQueue);
        }  catch (Exception e) {
            log.error("Error sending inventory update message to queue {}", orderStatusUpdateQueue, e);
            throw new RuntimeException("Error sending inventory update message to queue " + orderStatusUpdateQueue, e);
        }
    }
}
