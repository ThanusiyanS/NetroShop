package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.Entity.Inventory;

import java.util.List;

public interface InventoryService {
    public Inventory createInventory(InventoryRequest inventoryRequest);
    public List<Inventory> getAllInventory();
    public Inventory getInventory(String id) throws Exception;
}
