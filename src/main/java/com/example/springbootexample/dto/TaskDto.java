package com.example.springbootexample.dto;

import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Task;
import com.example.springbootexample.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class TaskDto {

    private UUID id;

    private UUID creatorId;

    private UUID executorId;

    private TaskStatus status;

    private UUID parentId;

    private LocalDate deadline;
}
