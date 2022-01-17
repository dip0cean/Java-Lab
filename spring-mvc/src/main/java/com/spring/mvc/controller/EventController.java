package com.spring.mvc.controller;

import com.spring.mvc.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @GetMapping
    public String events(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "events";
    }
}
