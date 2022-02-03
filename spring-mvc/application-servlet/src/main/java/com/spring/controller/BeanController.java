package com.spring.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// BeanNameUrlHandlerMapping, BeanNameUrlHandlerAdapter 에 의해 처리된다.
@org.springframework.stereotype.Controller("/bean")
public class BeanController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return new ModelAndView("/WEB-INF/bean.jsp"); // Custom ViewResolver 를 설정하지 않았을 경우
        return new ModelAndView("bean");
    }
}
