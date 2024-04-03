package com.example.springbootexample.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionMessageObject> handleEntityNotFoundException(EntityNotFoundException exception) {
        ExceptionMessageObject message = ExceptionMessageObject.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
