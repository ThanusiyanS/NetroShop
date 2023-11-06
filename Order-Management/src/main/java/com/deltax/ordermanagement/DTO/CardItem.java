package com.deltax.ordermanagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CardItem {
    private String skuCode;
    private long quantity;
    private boolean isSelected;

}
