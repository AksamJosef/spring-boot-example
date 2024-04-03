package com.example.springbootexample;

public class CeoIsExistsException extends RuntimeException {
    public CeoIsExistsException(String message) {
        super(message);
    }
}
