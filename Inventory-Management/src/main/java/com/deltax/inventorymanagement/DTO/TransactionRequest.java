package com.deltax.inventorymanagement.DTO;

import com.deltax.inventorymanagement.Entity.Inventory;
import lombok.Data;

import java.util.List;

@Data
public class TransactionRequest {
    private String userId;
    private List<InventoryRequest> inventories;
    private TransactionType transactionType;
}

