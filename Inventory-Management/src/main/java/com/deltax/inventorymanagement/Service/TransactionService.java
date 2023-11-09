package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.TransactionRequest;
import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;

public interface TransactionService {
    public InventoryTransaction createTransaction(TransactionRequest transactionRequest) throws InventoryNotFoundException;
}
