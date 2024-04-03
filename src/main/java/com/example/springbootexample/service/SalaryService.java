package com.example.springbootexample.service;

import com.example.springbootexample.dto.SalaryDto;
import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Salary;
import com.example.springbootexample.enums.Month;
import com.example.springbootexample.mapper.SalaryMapper;
import com.example.springbootexample.repository.SalaryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {

    private final SalaryRepository salaryRepository;

    private final SalaryMapper mapper;

    public void createSalary(Map<Month, Integer> salaryByMonths, Employee employee) {
        salaryByMonths.forEach(((month, value) -> {
            Salary salary = Salary.builder()
                    .month(month)
                    .value(value)
                    .employee(employee)
                    .build();

            salaryRepository.save(salary);
        }));
    }

    public List<Salary> getSalariesByEmployee(Employee employee) {
        return salaryRepository.findAllByEmployee(employee);
    }

    public SalaryDto getSalaryById(UUID id) {
        return salaryRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Сущность не найдена!!!!!"));
    }
}
