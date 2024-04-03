package com.example.springbootexample.controller;

import com.example.springbootexample.dto.AddressDto;
import com.example.springbootexample.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;
    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return service.getAllAddresses();
    }

    @PutMapping
    public AddressDto editAddress(@RequestBody AddressDto addressDto) {
        return service.editAddress(addressDto);
    }
}
