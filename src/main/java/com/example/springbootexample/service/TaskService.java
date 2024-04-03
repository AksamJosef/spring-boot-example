package com.example.springbootexample.service;

import com.example.springbootexample.dto.TaskDto;
import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Task;
import com.example.springbootexample.mapper.TaskMapper;
import com.example.springbootexample.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    private final TaskMapper mapper;

    private final EmployeeService employeeService;

    public List<TaskDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public TaskDto createTask(TaskDto dto) {
        Employee creator = employeeService.getEmployeeById(dto.getCreatorId());
        Employee executor = employeeService.getEmployeeById(dto.getExecutorId());

        Task parent = ofNullable(dto.getParentId())
                .flatMap(repository::findById)
                .orElse(null);

        Task task = mapper.toEntity(dto).builder()
                .creator(creator)
                .executor(executor)
                .parent(parent)
                .status(dto.getStatus())
                .deadline(LocalDate.now())
                .build();

        Task savedTask = repository.save(task);

        return mapper.toDto(savedTask);
    }

    public List<TaskDto> getSubtask(UUID taskId) {
        return repository.findAllByParentId(taskId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public TaskDto getParent(UUID taskId) {
        Task task = repository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Таска не найдена"));

        Task parent = task.getParent();

        return mapper.toDto(parent);
    }
}
