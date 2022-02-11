package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UriArgumentController {

    @GetMapping("/uri/{id}")
    public Event getEvent(@PathVariable("id") Event event) {
        // Converter or Formatter 사용해서 컨버팅
        return event;
    }

    @GetMapping(value = {"/optional/{id}", "/optional"})
    public Event getEvent(@PathVariable Optional<String> id) {
        // Optional 을 이용해서 Null Check
        return Event.builder().id(id.orElse("2")).build();
    }

    @GetMapping("/matrix/{id}")
    public Event getEvent(@PathVariable String id, @MatrixVariable String name) {
        return Event.builder().id(id).name(name).build();
    }
}
