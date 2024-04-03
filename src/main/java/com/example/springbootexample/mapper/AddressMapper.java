package com.example.springbootexample.mapper;

import com.example.springbootexample.dto.AddressDto;
import com.example.springbootexample.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto dto);

    Address merge(AddressDto dto, @MappingTarget Address entity);
}
