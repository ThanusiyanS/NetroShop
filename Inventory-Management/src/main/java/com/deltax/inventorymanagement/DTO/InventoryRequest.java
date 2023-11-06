package com.deltax.inventorymanagement.DTO;

import lombok.Data;

@Data
public class InventoryRequest {

    private String productId;
    private long quantity;
}
