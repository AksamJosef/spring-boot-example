package com.example.springbootexample.controller;

import com.example.springbootexample.dto.RoleDto;
import com.example.springbootexample.service.RoleService;
import com.example.springbootexample.validation.RoleValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    private final RoleValidator validator;

    @PostMapping
    public void addRole(@RequestBody RoleDto roleDto, BindingResult bindingResult) {
        validator.validate(roleDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Уже присутствует роль " + roleDto);
        }

        roleService.addRole(roleDto);
    }
}
