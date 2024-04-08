package com.example.springbootexample.service;

import com.example.springbootexample.dto.CompanyDto;
import com.example.springbootexample.entity.Address;
import com.example.springbootexample.entity.Company;
import com.example.springbootexample.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public CompanyDto addAddress(CompanyDto companyDto) {
        UUID companyId = companyDto.getId();

        Address address = Address.builder()
                .postalCode(companyDto.getPostalCode())
                .country(companyDto.getCountry())
                .city(companyDto.getCity())
                .address(companyDto.getAddress())
                .build();

        Company company = getById(companyId);
        company.setAddress(address);

        Company savedEntity = companyRepository.save(company);

        return toDto(savedEntity);
    }

    private CompanyDto toDto(Company savedEntity) {
        return CompanyDto.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .postalCode(savedEntity.getAddress().getPostalCode())
                .country(savedEntity.getAddress().getCountry())
                .city(savedEntity.getAddress().getCity())
                .address(savedEntity.getAddress().getAddress())
                .build();
    }

    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAllCompanies().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Boolean createCompanies(CompanyDto companyDto) {

        Company company = Company.builder()
                .name(companyDto.getName())
                .address(Address.builder()
                        .postalCode(companyDto.getPostalCode())
                        .country(companyDto.getCountry())
                        .city(companyDto.getCity())
                        .address(companyDto.getAddress())
                        .build())
                .build();

        companyRepository.save(company);

        return Boolean.TRUE;
    }
}
