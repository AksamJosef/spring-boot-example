package com.example.springbootexample.service;

import com.example.springbootexample.dto.AddressDto;
import com.example.springbootexample.dto.CompanyDto;
import com.example.springbootexample.entity.Address;
import com.example.springbootexample.mapper.AddressMapper;
import com.example.springbootexample.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    private final AddressMapper mapper;

    public List<AddressDto> getAllAddresses() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public AddressDto editAddress(AddressDto addressDto) {
        Address address = repository.findById(addressDto.getId()).orElseThrow();

        Address savedEntity = repository.save(mapper.merge(addressDto, address));

        return mapper.toDto(savedEntity);
    }

    public CompanyDto getCompaniesByAddressId(UUID addressId) {

        Optional<Address> optionalAddress = repository.findById(addressId);

        return optionalAddress
                .map(address -> address.getCompany())
                .map(company -> CompanyDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .build())
                .orElseThrow();
    }
}
