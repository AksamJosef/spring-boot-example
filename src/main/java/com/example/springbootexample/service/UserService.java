package com.example.springbootexample.service;

import com.example.springbootexample.annotations.Encrypt;
import com.example.springbootexample.dto.RoleDto;
import com.example.springbootexample.dto.UserDto;
import com.example.springbootexample.entity.Role;
import com.example.springbootexample.entity.User;
import com.example.springbootexample.mapper.UserMapper;
import com.example.springbootexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;

    private final UserRepository repository;

    private final RoleService roleService;

    @Encrypt
    public UserDto addUser(UserDto userDto) {
        List<UUID> roleIds = userDto.getRoles().stream().map(RoleDto::getId).collect(Collectors.toList());

        Set<Role> roles = roleService.getRoles(roleIds);

        User entity = mapper.toEntity(userDto);
        entity.setRoles(roles);

        User savedEntity = repository.save(entity);

        UserDto dto = mapper.toDto(savedEntity);
        dto.setRoles(roles.stream()
                .map(role -> RoleDto.builder()
                        .id(role.getId())
                        .role(role.getRoleType().name())
                        .build())
                .collect(Collectors.toSet()));

        return dto;
    }
}
