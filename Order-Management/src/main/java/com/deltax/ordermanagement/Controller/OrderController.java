package com.deltax.ordermanagement.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    static String QUEUE_NAME = "order-confirmation"; // Change the queue name to reflect order confirmation

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public record OrderConfirmationDto(String orderId) {}

    @PostMapping("/confirmOrder") // Change the route to "/confirmOrder"
    public ResponseEntity<Map<String, String>> confirmOrder(@RequestBody OrderConfirmationDto orderConfirmationDto) throws JsonProcessingException {
        // Perform order confirmation logic here (you can customize this part)

        // Send a message to RabbitMQ
        ObjectMapper objectMapper = new ObjectMapper();
        String queuePayloadString = objectMapper.writeValueAsString(orderConfirmationDto);

        rabbitTemplate.convertAndSend(QUEUE_NAME, queuePayloadString);

        // Respond with a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Order confirmed successfully!");

        return ResponseEntity.ok(response);
    }
}
