package com.example.springbootexample.handler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionMessageObject {

    private HttpStatus status;

    private String message;

    private LocalDateTime dateTime;
}
