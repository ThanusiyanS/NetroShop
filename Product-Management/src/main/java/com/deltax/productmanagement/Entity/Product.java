package com.deltax.productmanagement.Entity;

import jakarta.validation.constraints.Min;
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
    private String skuCode;
    private String productName;
    @Min(value = 0, message = "Price should not be less than 0")
    private double price;
    private String productType;
    private String productDescription;


}
