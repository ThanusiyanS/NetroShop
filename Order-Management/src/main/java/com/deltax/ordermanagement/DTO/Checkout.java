package com.deltax.ordermanagement.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class Checkout {
    private String shippingAddress;
    private String paymentMtd;
}
