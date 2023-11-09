package com.deltax.ordermanagement.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserResponse {
    @Id
    private String userId;
    private String name;
    private String shippingAddress;
}
