package com.example.springbootexample.mapper;

import com.example.springbootexample.dto.SalaryDto;
import com.example.springbootexample.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SalaryMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    SalaryDto toDto(Salary salary);
}
