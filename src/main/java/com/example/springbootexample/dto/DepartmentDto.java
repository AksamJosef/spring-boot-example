package com.example.springbootexample.dto;

import com.example.springbootexample.enums.DepartmentKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {

    private UUID id;

    /**
     * Наименование отдела
     */
    private DepartmentKind name;

    private CompanyDto company;
}
