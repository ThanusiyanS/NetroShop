package com.deltax.inventorymanagement.Controler;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.DTO.InventoryResponse;
import com.deltax.inventorymanagement.Entity.Inventory;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;
import com.deltax.inventorymanagement.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
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
    @GetMapping("getBySkuCode")
    public Inventory getBySkucode(@RequestParam("skuCode") String skucode) throws InventoryNotFoundException {
        return inventoryService.getBySkucode(skucode);
    }

    @PostMapping("/getBySkucodes")
    public List<InventoryResponse> getByListOfSkuCodes(@RequestBody List<String> skuCodes){
        return inventoryService.getByListOfSkuCodes(skuCodes);
    }



}
