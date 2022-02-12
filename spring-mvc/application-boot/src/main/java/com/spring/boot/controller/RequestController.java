package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RequestController {

    // @RequestParam
    @GetMapping("/request")
    public Event getRequestParam(@RequestParam("id") Event event) {
        return event;
    }

    @GetMapping("/request/map")
    public Map<String, String> getRequestParams(@RequestParam Map<String, String> params) {
        return params;
    }

    @GetMapping("/request/required")
    public Event getRequired(@RequestParam String id,
                             @RequestParam String name) {
        return Event.builder().id(id).name(name).build();
    }

    // @ModelAttribute
    @GetMapping("/modelAttribute")
    public Event getModel(@ModelAttribute Event event) {
        return event;
    }

    @GetMapping("/modelAttribute/bindingResult")
    public String getModelByBindingResult(@ModelAttribute Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        }
        return event.getId();
    }

    // @Valid > Spring Boot 2.3.x 부터는 별도의 의존성을 추가해줘야 한다.
    // @ModelAttribute > 생략 가능하다.
    @GetMapping("/valid")
    public String valid(@Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        }
        return event.getId();
    }

    // @Validated
    @GetMapping("/validated")
    public String validated(@Validated(value = {User.JobCheck.class, User.AgeCheck.class}) User user,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()).toString();
        }
        return user.getId();
    }
}