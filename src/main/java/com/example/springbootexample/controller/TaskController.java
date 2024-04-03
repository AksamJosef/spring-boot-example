package com.example.springbootexample.controller;

import com.example.springbootexample.dto.TaskDto;
import com.example.springbootexample.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAll();
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        return taskService.createTask(dto);
    }

    @GetMapping("/subtasks")
    public List<TaskDto> getSubtask(@RequestParam UUID taskId) {
        return taskService.getSubtask(taskId);
    }

    @GetMapping("/get-parent")
    public TaskDto getParent(@RequestParam UUID taskId) {
        return taskService.getParent(taskId);
    }


}
