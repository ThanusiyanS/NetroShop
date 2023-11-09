package com.deltax.inventorymanagement.Service;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.DTO.InventoryResponse;
import com.deltax.inventorymanagement.DTO.Product;
import com.deltax.inventorymanagement.Entity.Inventory;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;
import com.deltax.inventorymanagement.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service

public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(InventoryRequest inventoryRequest) {
        Product product = webClientBuilder.build().get()
                .uri("http://PRODUCT-SERVICE/products/get/" + inventoryRequest.getProductId())
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        assert product != null;


        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getProductId());
        inventory.setProductName(product.getProductName());
        inventory.setQuantity(inventoryRequest.getQuantity());
        return inventoryRepository.save(inventory);


    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getBySkucode(String id) throws InventoryNotFoundException {
        return inventoryRepository.findById(id).orElseThrow(InventoryNotFoundException::new);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<InventoryResponse> getByListOfSkuCodes(List<String> skuCodes) {
        return inventoryRepository.findInventoryBySkuCodeIn(skuCodes);
    }
}



