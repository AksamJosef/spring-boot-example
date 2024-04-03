package com.example.springbootexample.service;

import com.example.springbootexample.entity.Company;
import com.example.springbootexample.entity.Department;
import com.example.springbootexample.enums.DepartmentKind;
import com.example.springbootexample.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final CompanyService companyService;

    public Department getById(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public UUID createNewDepartment(DepartmentKind kind, UUID companyId) {
        Company company = companyService.getById(companyId);

        Department department = Department.builder()
                .name(kind)
                .company(company)
                .build();

        Department savedDepartment = departmentRepository.save(department);

        return savedDepartment.getId();
    }

    public Department getByKind(DepartmentKind kind) {
        return departmentRepository.findByName(kind)
                .orElseThrow();
    }
}
