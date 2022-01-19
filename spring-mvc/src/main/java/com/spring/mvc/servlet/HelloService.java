package com.spring.mvc.servlet;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getHello() {
        return "Hello o-sean!";
    }
}
