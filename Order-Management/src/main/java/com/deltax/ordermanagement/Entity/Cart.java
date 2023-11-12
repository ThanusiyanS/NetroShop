package com.deltax.ordermanagement.Entity;

import com.deltax.ordermanagement.DTO.CartItem;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Setter
@Getter
public class Cart {
    @Id
    private String cartId;
    private String userId;
    private List<CartItem> cartItems;
    @Min(value = 0, message = "Price should not be less than 0")
    private double totalPrice;
}
