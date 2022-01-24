package com.spring.controller;

import com.spring.servlce.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// RequestMappingHandlerMapping, RequestMappingHandlerAdapter 에 의해 처리된다.
@Controller
public class RequestMappingController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/requestBody")
    @ResponseBody
    public String requestBody() {
        return helloService.sayHello();
    }

    @GetMapping("/viewResolver")
    public String viewResolver() {

//        return "/WEB-INF/viewResolver.jsp"; // Custom ViewResolver 를 설정하지 않았을 경우
        return "viewResolver";
    }
}
