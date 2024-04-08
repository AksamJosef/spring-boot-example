package com.example.springbootexample.controller;

import com.example.springbootexample.dto.AddressDto;
import com.example.springbootexample.dto.CompanyDto;
import com.example.springbootexample.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping("/company")
    public CompanyDto getCompany(@RequestParam UUID addressId) {
        return service.getCompaniesByAddressId(addressId);
    }

    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return service.getAllAddresses();
    }

    @PutMapping
    public AddressDto editAddress(@RequestBody AddressDto addressDto) {
        return service.editAddress(addressDto);
    }
}
