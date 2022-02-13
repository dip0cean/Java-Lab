package com.spring.boot.controller;

import com.spring.boot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes("users")
public class SessionController {

    // HttpSession
    @GetMapping("/session")
    public String getSession(@Validated(User.JobCheck.class) User user,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            user = User
                    .builder()
                    .id("1")
                    .name("Sam")
                    .jobs(new String[]{"student", "teacher"})
                    .build();
        }

        model.addAttribute("user", user);
        httpSession.setAttribute("user", user);

        return "/index";
    }

    // @SessionAttributes
    @GetMapping("/sessionAttributes")
    public String getSession(@Validated(User.AgeCheck.class) User user,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            user = User
                    .builder()
                    .id("1")
                    .name("Marine")
                    .age(16)
                    .build();
        }

        List<User> users = Collections.singletonList(user);
        model.addAttribute("users", users);

        return "/index";
    }

    @PostMapping("/setComplete")
    public String getSession(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "/index";
    }

    // @SessionAttribute
    @GetMapping("/sessionAttribute")
    public String getSession(Model model,
                             @SessionAttribute LocalDateTime visit) {
        model.addAttribute("visit", visit);
        return "/index";
    }
}
