package com.example.springbootexample.service;

import com.example.springbootexample.dto.RoleDto;
import com.example.springbootexample.entity.Role;
import com.example.springbootexample.enums.RoleType;
import com.example.springbootexample.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public void addRole(RoleDto role) {
        repository.save(Role.builder()
                .roleType(RoleType.fromString(role.getRole()))
                .build());
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Set<Role> getRoles(List<UUID> roleIds) {
        return roleIds.stream()
                .map(repository::findById)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
