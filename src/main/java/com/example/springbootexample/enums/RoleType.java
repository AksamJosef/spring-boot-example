package com.example.springbootexample.enums;

import com.example.springbootexample.entity.Role;

import java.util.Arrays;
import java.util.Objects;

public enum RoleType {

    ROLE_ADMIN,

    ROLE_USER;

    public static RoleType fromString(String role) {
        return Arrays.stream(RoleType.values())
                .filter(type -> Objects.equals(role, type.name()))
                .findAny()
                .orElse(null);
    }
}
