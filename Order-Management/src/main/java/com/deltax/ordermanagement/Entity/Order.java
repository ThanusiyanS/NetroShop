package com.deltax.ordermanagement.Entity;

import com.deltax.ordermanagement.DTO.Checkout;
import com.deltax.ordermanagement.DTO.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@Getter
@Setter
public class Order {
    @Id
    private String orderId;
    private String userId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private LocalDateTime orderTime;
    private Checkout checkout;
}

enum OrderStatus {
    PENDING, PROCESSING, DELIVERED
}