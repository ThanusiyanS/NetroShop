package com.deltax.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private static final String RECIPIENT_EMAIL = "matheshyogeswaran@gmail.com";
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOrderConfirmationEmail(String to, String orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Order Confirmation");
        message.setText("Your order with ID " + orderId + " has been confirmed. Thank you for your purchase!");

        javaMailSender.send(message);
    }
}

