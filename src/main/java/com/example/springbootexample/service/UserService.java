package com.example.springbootexample.service;

import com.example.springbootexample.annotations.Encrypt;
import com.example.springbootexample.dto.RoleDto;
import com.example.springbootexample.dto.UserDto;
import com.example.springbootexample.entity.Role;
import com.example.springbootexample.entity.User;
import com.example.springbootexample.mapper.UserMapper;
import com.example.springbootexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public UserDto getCurrent() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();

            return repository.findByUsername(username)
                    .map(mapper::toDto)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь с указанным username не найден"));
        }

        return null;
    }

    public Boolean banUser(UUID id) {
        return repository.findById(id)
                .map(user -> {
                    user.setIsLocked(true);
                    repository.save(user);
                    return Boolean.TRUE;
                })
                .orElse(Boolean.FALSE);
    }
}
