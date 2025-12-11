package com.shoumik.orderprocessing.service;

import com.shoumik.orderprocessing.dto.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumerService {
    @JmsListener(destination = "${queue.order.notification}")
    public void processNotification(NotificationMessage message) {
        log.info("Received message from queue: " + message);
        try{
            Thread.sleep(500);
            if("EMAIL".equals(message.getNotificationType())){
                sendEmail(message);
            } else if("SMS".equals(message.getNotificationType())){
                sendSMS(message);
            }
        } catch ( Exception e){
            log.error(e.getMessage());
        }
    }
    private void sendEmail(NotificationMessage message) {
        log.info("Send email to {}",message.getCustomerMail());
        log.info("Subject is {}",message.getSubject());
        log.info("Message is {}",message.getMessage());
    }
    public void sendSMS(NotificationMessage message) {
        log.info("Send SMS to {}",message.getCustomerId());
        log.info("message is {}",message.getMessage());
    }
}
