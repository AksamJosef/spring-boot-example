package com.example.springbootexample.validation;

import com.example.springbootexample.dto.EmployeeDto;
import com.example.springbootexample.entity.Employee;
import com.example.springbootexample.service.EmployeeService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) target;

        Optional<Employee> employee = employeeService.getEmployeeByName(employeeDto.getName());

        if (employee.isPresent()) {
            errors.rejectValue("name", "", "Такой пользователь существует!");
        }
    }
}
