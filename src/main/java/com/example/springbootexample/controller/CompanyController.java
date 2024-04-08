package com.example.springbootexample.controller;

import com.example.springbootexample.dto.CompanyDto;
import com.example.springbootexample.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PutMapping
    public CompanyDto addAddress(@RequestBody CompanyDto companyDto) {
        return companyService.addAddress(companyDto);
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Boolean initCompanies(@RequestBody CompanyDto companyDto) {
        return companyService.createCompanies(companyDto);
    }
}
