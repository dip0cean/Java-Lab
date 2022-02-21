package com.spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.domain.Event;
import com.spring.boot.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {

    private final ObjectMapper mapper;

    // 여러 데이터를 Model 에 담아야 할 때는 핸들러 메소드의 아규먼트로 Model 객체를 받아와 attribute 를 추가한다.
    @ModelAttribute
    public void addUsers(Model model) {
        model.addAttribute("users", List.of(
                User.builder().id("1").name("Tom").build(),
                User.builder().id("2").name("Dan").build(),
                User.builder().id("3").name("Lisa").build(),
                User.builder().id("4").name("Rose").build(),
                User.builder().id("5").name("Chris").build()
        ));
        model.addAttribute("locale", Locale.KOREA);
    }

    // ModelAttribute 가 적용된 핸들러 메소드의 리턴이 존재하는 경우, @ModelAttribute 의 네이밍을 설정해주면 된다.
    @ModelAttribute("event")
    public Event addEvent() {
        return Event.builder().id("1").name("행복한 세상의 족제비").build();
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public String getUsers(Model model) throws JsonProcessingException {
        Locale locale = (Locale) model.getAttribute("locale");
        if (locale != null && locale.equals(Locale.KOREA)) {
            model.addAttribute("users", List.of(
                    User.builder().id("1").name("짱구").build(),
                    User.builder().id("2").name("철수").build(),
                    User.builder().id("3").name("유리").build(),
                    User.builder().id("4").name("훈이").build(),
                    User.builder().id("5").name("맹구").build()
            ));
        }
        return mapper.writeValueAsString(model.getAttribute("users"));
    }

    @GetMapping("/getEvent")
    @ResponseBody
    public String getEvent(Model model) throws JsonProcessingException {
        Locale locale = (Locale) model.getAttribute("locale");
        if (locale != null && locale.equals(Locale.KOREA)) {
            model.addAttribute("event", Event.builder().id("1").name("족제비 in Unhappy World").build());
        }
        return mapper.writeValueAsString(model.getAttribute("event"));
    }

    @GetMapping("/getModel")
    public ModelAndView getModel() {
        return new ModelAndView("/index");
    }
}
