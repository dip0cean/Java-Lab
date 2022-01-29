package com.spring.mvc.controller;

import com.spring.mvc.model.Dog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/interceptor")
public class InterceptorController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam("id") Dog dog) {
        return "good boy, " + dog.getName();
    }

    @GetMapping("/another")
    public String another(@RequestParam("id") Dog dog) {
        return "good girl, " + dog.getName();
    }
}
