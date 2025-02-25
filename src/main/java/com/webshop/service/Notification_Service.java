package com.webshop.service;
import com.webshop.model.Product;
import com.webshop.repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Notification_Service {
    @Autowired
    private Product_Repository productRepository;

    @Autowired
    private Email_Sending_Service emailSendingService;

    @Scheduled(fixedRate = 3600000) // Check every 1 hour
    public void checkStockLevels() {
        Iterable<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getStock() < 5) {
                emailSendingService.sendEmail("afreen.zehra1512@gmail.com", "Low Stock Alert", "Product "
                        + product.getName() + " is low on stock.");
            }
        }
    }
}