package com.deltax.inventorymanagement.DTO;

import lombok.Data;

@Data
public class Product {

        private String id;
        private String productName;
        private double price;
        private String productType;
        private String productDescription;
}
