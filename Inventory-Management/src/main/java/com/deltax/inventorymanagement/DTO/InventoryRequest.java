package com.deltax.inventorymanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class InventoryRequest {
    private String skuCode;
    @Min(value = 0, message = "Quantity should not be less than 0")
    private long quantity;
}
