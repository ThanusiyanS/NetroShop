package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.DTO.TransactionRequest;
import com.deltax.inventorymanagement.Entity.Inventory;
import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;
import com.deltax.inventorymanagement.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final InventoryService inventoryService;
    @Override
    public void createTransaction(TransactionRequest transactionRequest) throws InventoryNotFoundException {

        for (InventoryRequest orderedInventory : transactionRequest.getInventories()){
            Inventory inventory= inventoryService.getBySkucode(orderedInventory.getSkuCode());
            switch (transactionRequest.getTransactionType()){
                case PURCHASE, RETURN:
                     inventory.setQuantity(inventory.getQuantity() + orderedInventory.getQuantity());
                    break;
                case SALE:
                    inventory.setQuantity(inventory.getQuantity() - orderedInventory.getQuantity());
                    break;
            }
            inventoryService.updateInventory(inventory);

        }

        InventoryTransaction inventoryTransaction = new InventoryTransaction();
        inventoryTransaction.setInventories(transactionRequest.getInventories());
        inventoryTransaction.setTransactionType(transactionRequest.getTransactionType());
        inventoryTransaction.setUserId(transactionRequest.getUserId());
        inventoryTransaction.setCTransactionTime();

        transactionRepository.save(inventoryTransaction);
    }

    @Override
    public List<InventoryTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
