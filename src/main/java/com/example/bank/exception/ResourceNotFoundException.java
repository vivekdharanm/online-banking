package com.example.bank.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Default constructor with a generic message
    public ResourceNotFoundException() {
        super("Resource not found.");
    }

    // Constructor with a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
