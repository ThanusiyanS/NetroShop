package com.deltax.inventorymanagement.Repository;

import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<InventoryTransaction,String> {
}
