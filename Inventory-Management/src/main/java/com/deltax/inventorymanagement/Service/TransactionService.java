package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.TransactionRequest;
import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;

import java.util.List;

public interface TransactionService {
    public void createTransaction(TransactionRequest transactionRequest) throws InventoryNotFoundException;

    public List<InventoryTransaction> getAllTransactions();
}
