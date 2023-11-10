package com.deltax.ordermanagement.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
public class CartItem {
    private String skuCode;
    @Min(value = 0, message = "Quantity should not be less than 0")
    private long quantity;
    private boolean isSelected=false;

}
