package com.deltax.deliverymanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderConfirmationConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderConfirmationConsumer.class);

    @RabbitListener(queues = "order-confirmation")
    public void receiveOrderConfirmationMessage(String orderConfirmationJson) {
        // Parse the JSON message to extract the order ID
        // You can use Jackson ObjectMapper or another JSON library

        String orderId = extractOrderId(orderConfirmationJson);

        // Log the order ID
        logger.info("Received order confirmation for Order ID: " + orderId);

        // Handle the order ID as needed in your delivery service
    }

    private String extractOrderId(String orderConfirmationJson) {
        // Implement your logic to extract the order ID from the JSON message
        // Example: Use Jackson ObjectMapper to deserialize the JSON
        // ObjectMapper objectMapper = new ObjectMapper();
        // OrderConfirmationPayload payload = objectMapper.readValue(orderConfirmationJson, OrderConfirmationPayload.class);
        // String orderId = payload.getOrderId();

        // For simplicity, let's assume the order ID is just the entire message
        return orderConfirmationJson;
    }
}
