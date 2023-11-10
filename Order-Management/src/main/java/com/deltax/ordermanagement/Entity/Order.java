package com.deltax.ordermanagement.Entity;

import com.deltax.ordermanagement.DTO.Checkout;
import com.deltax.ordermanagement.DTO.OrderItem;
import com.deltax.ordermanagement.ENUM.OrderStatus;
import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "Price should not be less than 0")
    private double totalAmount;
    private Checkout checkout;
}

