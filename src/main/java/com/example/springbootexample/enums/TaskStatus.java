package com.example.springbootexample.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {

    NEW("Новая"),

    CLOSED("Закрыта"),

    IN_WORK("В работе");

    private final String value;
}
