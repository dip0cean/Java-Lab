package com.spring.basic.chapter_09_Validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidatorAppRunner implements ApplicationRunner {

    private final Validator validator;
    private final UserValidator userValidator;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = new User();
        user.setId("test-user");
        user.setPassword("2022 Hello Wold!");
        user.setEmail("is Not Email");
        user.setUserName("심테스트");
        user.setChild(new int[]{0, 10, 100});

        Errors errors = new BeanPropertyBindingResult(user, "user");
        log.info("LocalValidatorFactoryBean.class :: {}", validator.getClass());
        validator.validate(user, errors);
        log.info("UserValidator.class :: {}", userValidator.getClass());
        userValidator.validate(user, errors);

        log.info("hasError? :: {}", errors.hasErrors());
        errors.getAllErrors().forEach(error -> {
            Arrays.stream(Objects.requireNonNull(error.getCodes())).forEach(code -> log.info("errorCode :: {}", code));
            log.info("errorMessage :: {}", error.getDefaultMessage());
        });
    }
}
