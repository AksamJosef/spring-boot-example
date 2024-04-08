package com.example.springbootexample.validation;

import com.example.springbootexample.dto.RoleDto;
import com.example.springbootexample.entity.Role;
import com.example.springbootexample.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RoleValidator implements Validator {

    private final RoleService roleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RoleDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RoleDto targetRole = (RoleDto) target;

        boolean exists = roleService.findAll().stream()
                .anyMatch(role -> targetRole.getRole().equals(role.getRoleType().name()));

        if (exists) {
            errors.rejectValue("role", "");
        }
    }
}
