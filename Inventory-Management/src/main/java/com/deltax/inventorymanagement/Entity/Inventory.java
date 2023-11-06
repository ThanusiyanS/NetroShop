package com.deltax.inventorymanagement.Entity;

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
    private String inventoryId;
    private String skuCode;
    private String productName;
    private long quantity;

}
