package com.spring.basic.chapter_09_Validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull
    private String id;

    @NotNull
    @Max(5)
    private String password;

    @Email
    private String email;

    @NotNull
    private String userName;

    private Object child;

    private String testField;
}
