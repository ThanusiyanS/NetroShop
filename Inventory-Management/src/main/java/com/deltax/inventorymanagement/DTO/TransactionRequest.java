package com.deltax.inventorymanagement.DTO;

import lombok.Data;

@Data
public class TransactionRequest {
    private String skuCode;
    private long quantity;
    private TransactionType transactionType;
}

