package com.deltax.inventorymanagement.DTO;

import lombok.Data;

@Data
public class InventoryRequest {
    private String skuCode;
    private long quantity;
}
