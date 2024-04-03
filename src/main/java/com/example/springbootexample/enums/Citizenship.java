package com.example.springbootexample.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Citizenship {

    RU("Российская Федерация"),

    KZ("Казахстан"),

    US("США"),

    GE("Германия");

    private final String value;
}
