package com.deltax.inventorymanagement.Controler;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.Entity.Inventory;
import com.deltax.inventorymanagement.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v")
public class InventoryConroller {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public Inventory createInventory(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.createInventory(inventoryRequest);
    }
    @GetMapping("/getAll")
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventory();
    }
    @GetMapping("{inventoryId}")
    public Inventory getInventory(@PathVariable String inventoryId) throws Exception {
        return inventoryService.getInventory(inventoryId);
    }



}
