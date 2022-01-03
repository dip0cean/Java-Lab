package com.osean.springinaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 해당 클래스가 Controller 라는 것을 ComponentScan 이 찾아서
// Spring Application Context 에 등록할 수 있도록하는 어노테이션
@Controller
public class HomeController {

    // GetMapping > 지정된 URL 의 GET 요청을 처리하도록 한다.
    @GetMapping("/")
    public String home() {
        return "home"; // ViewResolver 에 의해 해당 뷰 템플릿의 경로로 찾아서 반환한다.
    }
}
