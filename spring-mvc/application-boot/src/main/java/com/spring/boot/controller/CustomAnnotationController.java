package com.spring.boot.controller;


import com.spring.boot.annotation.GetHelloMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomAnnotationController {

    @GetHelloMapping
    public String hello() {
        return "Hello!";
    }
}
