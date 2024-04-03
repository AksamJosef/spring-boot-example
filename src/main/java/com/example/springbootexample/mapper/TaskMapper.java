package com.example.springbootexample.mapper;

import com.example.springbootexample.dto.TaskDto;
import com.example.springbootexample.entity.Task;
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
public interface TaskMapper {

    @Mapping(source = "creatorId", target = "creator.id", ignore = true)
    @Mapping(source = "executorId", target = "executor.id", ignore = true)
    @Mapping(source = "parentId", target = "parent.id", ignore = true)
    Task toEntity(TaskDto dto);

    @Mapping(target = "creatorId", source = "creator.id")
    @Mapping(target = "executorId", source = "executor.id")
    @Mapping(target = "parentId", source = "parent.id")
    TaskDto toDto(Task task);
}
