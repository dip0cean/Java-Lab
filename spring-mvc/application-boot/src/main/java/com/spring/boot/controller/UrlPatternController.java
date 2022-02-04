package com.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlPatternController {

    @GetMapping("/questionMark/?")
    public String questionMark() {
        return "한 글자만 경로로 인식합니다.";
    }

    @GetMapping("/asterisk/*")
    public String asterisk() {
        return "/ 뒤의 한 단어만 경로로 인식합니다.";
    }

    @GetMapping("/asterisks/**")
    public String asterisks() {
        return "/ 뒤의 여러 Path 를 경로로 인식합니다.";
    }

    @GetMapping("/regex/{name:[a-z]+}")
    public String regex(@PathVariable("name") String regex) {
        return "정규표현식을 경로로 인식합니다. 값 : " + regex;
    }

    @GetMapping("/asterisk/love")
    public String love() {
        return "경로가 중복되는 경우 더 구체적인 핸들러를 선택합니다.";
    }
}
