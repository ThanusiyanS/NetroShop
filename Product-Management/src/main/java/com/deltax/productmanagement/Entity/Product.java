package com.deltax.productmanagement.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Getter
@Setter
public class Product {
    @Id
    private String id;
    private String productName;
    private double price;
    private String productType;

}
