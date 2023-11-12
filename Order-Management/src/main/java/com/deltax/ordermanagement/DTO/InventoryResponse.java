package com.deltax.ordermanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class InventoryResponse {
    @Id
    private String skuCode;
    private String productName;
    @Min(value = 0, message = "Quantity should not be less than 0")
    private long quantity;
}
