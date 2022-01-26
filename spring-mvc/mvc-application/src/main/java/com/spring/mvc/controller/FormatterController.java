package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormatterController {

    @ResponseBody
    @GetMapping("/formatter")
    public String formatter() {
        return "Hello, Beautiful World!";
    }
}
