package com.spring.servlce;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello() {
        return "new Hello~!";
    }
}
