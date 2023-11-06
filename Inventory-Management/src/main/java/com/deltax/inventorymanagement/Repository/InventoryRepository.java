package com.deltax.inventorymanagement.Repository;

import com.deltax.inventorymanagement.Entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
}
