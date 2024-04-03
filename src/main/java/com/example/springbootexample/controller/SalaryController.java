package com.example.springbootexample.controller;

import com.example.springbootexample.dto.SalaryDto;
import com.example.springbootexample.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService service;

    @GetMapping
    public SalaryDto getSalaryById(@RequestParam UUID id) {
        return service.getSalaryById(id);
    }
}
