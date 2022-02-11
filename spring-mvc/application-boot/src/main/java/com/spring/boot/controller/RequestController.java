package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RequestController {

    @GetMapping("/request")
    public Event getRequestParam(@RequestParam("id") Event event) {
        return event;
    }

    @GetMapping("/request/map")
    public Map<String, String> getRequestParams(@RequestParam Map<String, String> params) {
        return params;
    }

    @GetMapping("/request/required")
    public Event getRequired(@RequestParam String id,
                             @RequestParam String name) {
        return Event.builder().id(id).name(name).build();
    }
}
