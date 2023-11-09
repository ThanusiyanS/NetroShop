package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.Product;
import com.deltax.inventorymanagement.DTO.TransactionRequest;
import com.deltax.inventorymanagement.Entity.Inventory;
import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;
import com.deltax.inventorymanagement.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final InventoryService inventoryService;
    @Override
    public InventoryTransaction createTransaction(TransactionRequest transactionRequest) throws InventoryNotFoundException {

        Inventory inventory = inventoryService.getBySkucode(transactionRequest.getSkuCode());

        switch (transactionRequest.getTransactionType()){
            case PURCHASE, RETURN:
                inventory.setQuantity(inventory.getQuantity() + transactionRequest.getQuantity());
                break;
            case SALE:
                inventory.setQuantity(inventory.getQuantity() - transactionRequest.getQuantity());
                break;
        }

        inventoryService.updateInventory(inventory);

        InventoryTransaction inventoryTransaction = new InventoryTransaction();
        inventoryTransaction.setSkuCode(transactionRequest.getSkuCode());
        inventoryTransaction.setQuantity(transactionRequest.getQuantity());
        inventoryTransaction.setTransactionType(transactionRequest.getTransactionType());
        inventoryTransaction.setCTransactionTime();

        return transactionRepository.save(inventoryTransaction);
    }
}
