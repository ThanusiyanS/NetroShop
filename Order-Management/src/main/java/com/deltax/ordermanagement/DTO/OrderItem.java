package com.deltax.ordermanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrderItem {
    String skuCode;
    @Min(value = 0, message = "Quantity should not be less than 0")
    long quantity;
}
