package com.spring.boot.valid;

import com.spring.boot.domain.Event;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Event.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if (event.getName().equals("Bad Event")) {
            errors.rejectValue("name", "wrongValue", "the value is not allowed.");
        }
    }
}
