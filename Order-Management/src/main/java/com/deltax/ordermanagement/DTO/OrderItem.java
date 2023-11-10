package com.deltax.ordermanagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrderItem {
    String skuCode;
    long quantity;
}
