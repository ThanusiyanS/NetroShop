package com.deltax.inventorymanagement.Exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException() {
        super("Inventory Not Found");
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }
}