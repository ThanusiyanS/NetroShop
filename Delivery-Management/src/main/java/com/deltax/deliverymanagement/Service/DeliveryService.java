package com.deltax.deliverymanagement.Service;
import com.deltax.deliverymanagement.Entity.DeliveryStatus;
import com.deltax.deliverymanagement.Repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.Header;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
    @Autowired
    private DeliveryRepository deliveryRepository;
    private final AmqpTemplate amqpTemplate;

    public DeliveryService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
    @RabbitListener(queues = "delivery-queue")
    public void processOrder(String acknowledgmentMessage,
                             @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
                             @Headers Map<String, Object> headers) {
        // Log the acknowledgment message first
        //logger.info("Received acknowledgment: {}", acknowledgmentMessage);

        // Check if the message was received from the same service
        if ("netroshop-routing-key".equals(routingKey)) {
            logger.info("Received acknowledgment for the message sent by the same service. Avoiding loop.");
            return;
        }

        // Extract orderId and status from headers
        String orderId = (String) headers.get("orderId");
        String status = (String) headers.get("status");

        // Log orderId and status
        logger.info("Received orderId: {}", orderId);
        logger.info("Received status: {}", status);

        createDelivery(new DeliveryStatus(orderId, status));

        // Example: Sending a simple acknowledgment message
        String acknowledgment = acknowledgmentMessage;
        amqpTemplate.convertAndSend("netroshop-exchange", "netroshop-routing-key", acknowledgment);
    }



//    @RabbitListener(queues = "delivery-queue")
//    public void processOrder(String acknowledgmentMessage,
//                             @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
//                             @Headers Map<String, Object> headers) {
//        // Log the acknowledgment message first
//        logger.info("Received acknowledgment: {}", acknowledgmentMessage);
//
//        // Check if the message was received from the same service
//        if ("netroshop-routing-key".equals(routingKey)) {
//            logger.info("Received acknowledgment for the message sent by the same service. Avoiding loop.");
//            return;
//        }
//
//        // Extract orderId and status from headers
//        String orderId = (String) headers.get("orderId");
//        String status = (String) headers.get("status");
//
//
//        // Log orderId and status
//        logger.info(orderId);
//        logger.info(status);
//
//        // Process the order and send an acknowledgment if necessary
//        // ...
//
//        // Example: Sending a simple acknowledgment message
//        String acknowledgment = acknowledgmentMessage;
//        amqpTemplate.convertAndSend("netroshop-exchange", "netroshop-routing-key", acknowledgment);
//    }

//    @RabbitListener(queues = "delivery-queue")
//    public void processOrder(String acknowledgmentMessage, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
//        // Log the acknowledgment message first
//        logger.info("Received acknowledgment: {}", acknowledgmentMessage);
//
//        // Check if the message was received from the same service
//        if ("netroshop-routing-key".equals(routingKey)) {
//            logger.info("Received acknowledgment for the message sent by the same service. Avoiding loop.");
//            return;
//        }
//
//        // Process the order and send an acknowledgment if necessary
//        // ...
//
//        // Example: Sending a simple acknowledgment message
//        String acknowledgment = acknowledgmentMessage;
//        amqpTemplate.convertAndSend("netroshop-exchange", "netroshop-routing-key", acknowledgment);
//    }


    public void updateOrderStatus(String orderId, String newStatus) {
        DeliveryStatus deliveryStatus = deliveryRepository.findById(orderId).orElse(null);

        if (deliveryStatus != null) {
            deliveryStatus.setStatus(newStatus);
            deliveryRepository.save(deliveryStatus);
        }
    }

    public DeliveryStatus getOrderStatus(String orderId) {
        return deliveryRepository.findById(orderId).orElse(null);
    }

    public void createDelivery(DeliveryStatus deliveryStatus) {
        // Additional logic if needed
        deliveryRepository.save(deliveryStatus);
    }
}
