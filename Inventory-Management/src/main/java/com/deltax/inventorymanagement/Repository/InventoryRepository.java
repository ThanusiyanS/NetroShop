package com.deltax.inventorymanagement.Repository;

import com.deltax.inventorymanagement.Entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
}
