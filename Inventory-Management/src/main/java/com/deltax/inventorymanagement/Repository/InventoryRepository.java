package com.deltax.inventorymanagement.Repository;

import com.deltax.inventorymanagement.DTO.InventoryResponse;
import com.deltax.inventorymanagement.Entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory,String> {

    @Query(value = "{'skuCode':{$in:?0}}",fields = "{'skuCode':1,'productName':1,'quantity':1}")
    List<InventoryResponse> findInventoryBySkuCodeIn(List<String> skuCodes);
}
