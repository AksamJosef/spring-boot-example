package com.example.springbootexample.service;

import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.entity.Salary;
import com.example.springbootexample.enums.Month;
import com.example.springbootexample.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalaryService {

    private final SalaryRepository salaryRepository;

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
}
