package com.example.springbootexample.dto;

import com.example.springbootexample.enums.Citizenship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private UUID id;

    private String name;

    private String lastName;

    private Integer salary;

    private String position;

    private Citizenship citizenship;

    private LocalDate employmentDate;

    private DepartmentDto department;

}
