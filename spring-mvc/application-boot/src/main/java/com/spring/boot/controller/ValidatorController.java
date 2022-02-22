package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.valid.EventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ValidatorController {

    private final EventValidator eventValidator; // 특정 핸들러 메소드에서만 사용할 경우 주입 받아서 사용한다.

    @InitBinder
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new EventValidator()); // 해당 컨트롤러에서 전역적으로 사용할 경우 InitBinder 를 통해 등록한다.
    }

    @GetMapping("/validator")
    public Event getEvent(@Validated Event event,
                          BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return event;
    }
}
