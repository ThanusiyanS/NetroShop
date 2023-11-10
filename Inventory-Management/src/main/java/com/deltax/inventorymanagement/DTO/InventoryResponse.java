package com.deltax.inventorymanagement.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class InventoryResponse {
    @Id
    private String skuCode;
    private String productName;
    private long quantity;
}
