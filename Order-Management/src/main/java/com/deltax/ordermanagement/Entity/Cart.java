package com.deltax.ordermanagement.Entity;

import com.deltax.ordermanagement.DTO.CartItem;
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
    private double totalPrice;
}
