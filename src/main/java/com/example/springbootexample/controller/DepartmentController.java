package com.example.springbootexample.controller;

import com.example.springbootexample.enums.DepartmentKind;
import com.example.springbootexample.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/add-new-department")
    public UUID addNewDepartment(@RequestParam DepartmentKind kind, @RequestParam UUID companyId) {
        return departmentService.createNewDepartment(kind, companyId);
    }
}
