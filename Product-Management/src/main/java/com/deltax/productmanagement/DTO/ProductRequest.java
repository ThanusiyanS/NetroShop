package com.deltax.productmanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    @Min(value = 0, message = "Price should not be less than 0")
    private double price;
    private String productType;
    private String productDescription;
}
