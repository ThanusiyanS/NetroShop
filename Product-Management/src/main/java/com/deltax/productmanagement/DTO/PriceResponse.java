package com.deltax.productmanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PriceResponse {
    private String skuCode;
    @Min(value = 0, message = "Price should not be less than 0")
    private double price;
}
