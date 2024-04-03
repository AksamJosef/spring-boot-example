package com.example.springbootexample.controller;

import com.example.springbootexample.dto.EmployeeDto;
import com.example.springbootexample.enums.DepartmentKind;
import com.example.springbootexample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/add-default")
    public void createDefaultEmployee() {
        employeeService.addDefault();
    }

    @PostMapping("/init")
    public void initEmployees(@RequestParam UUID departmentId) {
        employeeService.initEmployees(departmentId);
    }

    @GetMapping("/sort-by-age")
    public List<EmployeeDto> sortByAge(@RequestParam Boolean fromBase, @RequestParam Integer age) {
        return employeeService.sortByAge(fromBase, age);
    }

    @GetMapping("/sort-by-salary")
    public List<EmployeeDto> sortBySalary(@RequestParam Integer salary) {
        return employeeService.sortBySalary(salary);
    }

    @GetMapping("/get-average")
    public Map<DepartmentKind, Double> getAverage() {
        return employeeService.getAverageSalary();
    }

    @GetMapping("/get-all")
    public List<EmployeeDto> getAllEmployeesDto() {
        return employeeService.getAllEmployeesDto();
    }

    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.addNewEmployee(employeeDto), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/edit")
    public EmployeeDto edit(@RequestBody EmployeeDto employeeDto) {
        return employeeService.editEmployee(employeeDto);
    }

    @GetMapping("/init-month-salary")
    public void initMonthSalary() {
        employeeService.initMonthSalary();
    }

    @GetMapping("/get-by-year")
    public Long getSalaryByYear(@RequestParam UUID employeeId) {
        return employeeService.getSalaryByYear(employeeId);
    }

    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }
}
