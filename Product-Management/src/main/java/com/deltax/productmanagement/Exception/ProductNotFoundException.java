package com.deltax.productmanagement.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product Not Found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
