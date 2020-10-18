package com.mytoys.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message, Long id) {
        super(message + id);
    }
}
