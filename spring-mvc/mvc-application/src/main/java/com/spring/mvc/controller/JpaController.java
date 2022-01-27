package com.spring.mvc.controller;

import com.spring.mvc.model.Dog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jpa")
public class JpaController {

    @GetMapping()
    public String requestParam(@RequestParam("id") Dog dog) {
        return String.format("I love you, %s", dog.getName());
    }
}
