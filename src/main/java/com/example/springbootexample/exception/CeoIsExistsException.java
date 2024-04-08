package com.example.springbootexample.exception;

public class CeoIsExistsException extends RuntimeException {
    public CeoIsExistsException(String message) {
        super(message);
    }
}
