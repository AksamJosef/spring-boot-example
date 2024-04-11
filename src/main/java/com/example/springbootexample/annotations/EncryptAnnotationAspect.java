package com.example.springbootexample.annotations;

import com.example.springbootexample.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class EncryptAnnotationAspect {

    private final PasswordEncoder passwordEncoder;


    @Pointcut("@annotation(Encrypt) && args(userDto,..)")
    public void callEncryptableMethod(UserDto userDto) {}

    @Before(value = "callEncryptableMethod(userDto)", argNames = "userDto")
    public void beforeCallEncryptableMethod(UserDto userDto) {
        System.out.println("------------------------------------");
        System.out.println("Пароль будет зашифрован!");
        System.out.println(userDto);

        String password = userDto.getPassword();

        String encodedPassword = passwordEncoder.encode(password);

        userDto.setPassword(encodedPassword);
    }

    @After(value = "callEncryptableMethod(userDto)", argNames = "userDto")
    public void afterCallEncryptableMethod(UserDto userDto) {
        System.out.println("------------------------------------");
        System.out.println("Сущность юзера сохранена в БД, пароль зашифрован");
        System.out.println(userDto);
    }
}
