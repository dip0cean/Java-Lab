package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {

    @ExceptionHandler({CustomException.class, RuntimeException.class})
    // 별도로 어떤 Exception 을 받을지 지정하면 해당 Exception 발생 시 적용된 핸들러 메소드에 맵핑된다.
    public String customExceptionHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "custom error");
        return "/error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String runtimeExceptionHandler(NullPointerException exception, Model model) {
        model.addAttribute("messgage", "null point error");
        return "/error";
    }

    @GetMapping("/exceptionHandler")
    @ResponseBody
    public Event getModel(@RequestParam(value = "id", required = false) Event event) {
        if (event.getId() == null) {
            throw new NullPointerException();
        }

        if (event.getId().equals("1")) {
            throw new CustomException();
        }

        if (event.getId().equals("2")) {
            // Exception 발생 시, 가장 구체적인 ExceptionHandler 로 맵핑된다.
            // 현재의 경우 RuntimeException 을 상속 받고 있는 구체적인 CustomException 에 해당하는 ExceptionHandler 가 맵핑된다.
            throw new RuntimeException();
        }

        return event;
    }
}
