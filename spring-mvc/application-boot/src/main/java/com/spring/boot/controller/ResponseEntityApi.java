package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ResponseEntityApi {

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return event;
    }

    @PostMapping("/httpEntity")
    public Event getHttpEntity(HttpEntity<Event> request) {
        MediaType contentType = request.getHeaders().getContentType();
        log.info("This Content-Type : {}", contentType);
        return request.getBody();
    }
}
