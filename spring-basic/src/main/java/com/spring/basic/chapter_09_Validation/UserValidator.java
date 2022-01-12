package com.spring.basic.chapter_09_Validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        // child 검증
        if (!(user.getChild() instanceof User)) {
            errors.rejectValue("child", "isNotUser", "해당 필드값이 User 객체가 아닙니다.");
        }

        // testField 검증
        if (user.getTestField() == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testField", "notNull", "해당 필드값이 없거나 공백입니다.");
        }
    }
}
