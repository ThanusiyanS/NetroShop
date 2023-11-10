package com.deltax.inventorymanagement.Entity;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Setter
@Getter
public class Inventory {
    @Id
    private String skuCode;
    private String productName;
    @Min(value = 0, message = "Quantity should not be less than 0")
    private long quantity;

}


