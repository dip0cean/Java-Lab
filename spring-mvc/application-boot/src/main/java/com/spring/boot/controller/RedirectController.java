package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("user")
public class RedirectController {

    @GetMapping("/redirect")
    public String getRedirect(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", "1");
        redirectAttributes.addAttribute("name", "newEvent");

        return "redirect:/redirect/event";
    }

    @GetMapping("/redirect/event")
    @ResponseBody
    public Event getRedirect(Event event) {
        return event;
    }

    @GetMapping("/redirect/error")
    public String getFoundError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", "1");
        redirectAttributes.addAttribute("name", "John");

        return "redirect:/redirect/user";
    }

    @GetMapping("/redirect/user")
    @ResponseBody
    public User getRedirect(@ModelAttribute User user) {
        return user;
    }
}
