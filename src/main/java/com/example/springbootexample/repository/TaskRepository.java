package com.example.springbootexample.repository;

import com.example.springbootexample.dto.TaskDto;
import com.example.springbootexample.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByParentId(UUID taskId);
}
