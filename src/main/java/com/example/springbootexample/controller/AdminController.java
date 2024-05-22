package com.example.springbootexample.controller;

import com.example.springbootexample.dto.UserDto;
import com.example.springbootexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Secured("ROLE_ADMIN")
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String test() {
        return "TEST";
    }

    @GetMapping("/current")
    public UserDto getCurrent() {
        return userService.getCurrent();
    }

    @GetMapping("/ban/{id}")
    public Boolean banUser(@PathVariable UUID id) {
        return userService.banUser(id);
    }
}
