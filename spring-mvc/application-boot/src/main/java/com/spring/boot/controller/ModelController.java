package com.spring.boot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.domain.Event;
import com.spring.boot.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getEvent/localDate")
    @ResponseBody
    public Event getEvent(Event event) {
        return event;
    }

    // 특정 바인더를 커스터마이징 할 수 있다.
    @InitBinder
    public void initEventBinder(WebDataBinder webDataBinder) {
        // 핸들러 메소드에서 받아오는 데이터 중 지정된 필드는 제외하고 가져온다.
        webDataBinder.setDisallowedFields("id"); // 블랙 리스트 기반 > 제외
        webDataBinder.setDisallowedFields("name"); // 화이트 리스트 기반 > 포함
    }
}
