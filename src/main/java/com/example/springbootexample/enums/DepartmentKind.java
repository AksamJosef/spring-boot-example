package com.example.springbootexample.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DepartmentKind {

    IT("Айтишники"),

    SERVICE("Сервис"),

    MANAGERS("Управление");

    private final String value;
}
