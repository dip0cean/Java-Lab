package com.spring.mvc.controller;

import com.spring.mvc.model.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/formatter")
public class FormatterController {

    @GetMapping
    public String formatter() {
        return "Hello, Beautiful World!";
    }

    @GetMapping("/pathVariable/{name}")
    public String pathVariable(@PathVariable("name") Person person) {
        return String.format("Hello, %s! Nice to meet you!", person.getName());
    }

    @GetMapping("/requestParam")
    public String requestParam(@RequestParam("name") Person person) {
        return String.format("%s is your brother?", person.getName());
    }
}
