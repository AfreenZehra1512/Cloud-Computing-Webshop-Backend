package com.webshop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email_Sending_Service {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendOrderPlacement(String to) {
        sendEmail(to, "Order Placed", "Your Order has been Placed Successfully!!!");
    }

    public void sendShippingNotification(String to) {
        sendEmail(to, "Shipping Notification", "Your Order has been Shipped!!!");
    }

    public void sendOrderConfirmation(String to) {
        sendEmail(to, "Order Confirmation", "Your )rder has been Confirmed!!!");
    }
}