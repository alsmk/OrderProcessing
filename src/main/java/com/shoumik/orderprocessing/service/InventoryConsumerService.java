package com.shoumik.orderprocessing.service;

import com.shoumik.orderprocessing.dto.InventoryUpdateMessage;
import com.shoumik.orderprocessing.model.Product;
import com.shoumik.orderprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryConsumerService {
    private final ProductRepository productRepository;
    @JmsListener(destination = "${queue.inventory.update}")
    public void processInventoryUpdate(InventoryUpdateMessage inventoryUpdateMessage){
        log.info("Received inventory update message for product: {}", inventoryUpdateMessage);
        Product product = productRepository.findById(inventoryUpdateMessage.getProductId()).orElseThrow(()-> new RuntimeException("Product not found"));
        int currentStock = product.getQuantity();
        int newStock = currentStock + inventoryUpdateMessage.getQuantityChange();
        if(newStock < 0 ){
            log.error("Quantity can not be negative");
            throw new RuntimeException("Insufficient stock");

        }
        product.setQuantity(newStock);
        productRepository.save(product);

    }
}
