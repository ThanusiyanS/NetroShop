package com.deltax.inventorymanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class Product {

        private String id;
        private String productName;
        @Min(value = 0, message = "Price should not be less than 0")
        private double price;
        private String productType;
        private String productDescription;
}
