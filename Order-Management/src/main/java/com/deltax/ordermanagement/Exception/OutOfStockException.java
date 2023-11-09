package com.deltax.ordermanagement.Exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Product Not Found");
    }

    public OutOfStockException(String message) {
        super(message);
    }
}

