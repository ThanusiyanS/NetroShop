package com.deltax.ordermanagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
public class CartItem {
    private String skuCode;
    private long quantity;
    private boolean isSelected=false;

}
