package com.example.springbootexample.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {

    private UUID id;

    /**
     * Наименование организации
     */
    private String name;

    private Integer postalCode;

    private String country;

    private String city;

    private String address;
}
