package com.deltax.productmanagement.DTO;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private double price;
    private String productType;
    private String productDescription;
}
