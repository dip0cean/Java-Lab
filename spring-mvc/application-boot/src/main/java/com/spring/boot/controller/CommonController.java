package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.exception.CustomException;
import com.spring.boot.valid.EventValidator;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

// 전역적으로 모든 컨트롤러에 적용된다.
@ControllerAdvice
public class CommonController {

    @ExceptionHandler(CustomException.class)
    public String customExceptionHandler(CustomException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "/error";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void setEvent(Model model) {
        Event event = Event.builder().id("999").name("other Event").build();
        model.addAttribute("otherEvent", event);
    }
}
