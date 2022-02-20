package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/api")
public class ResponseEntityApi {

    @PostMapping("/create")
    @ResponseBody
    public Event createEvent(@RequestBody Event event) {
        return event;
    }

    @PostMapping("/httpEntity")
    @ResponseBody
    public Event getHttpEntity(HttpEntity<Event> request) {
        MediaType contentType = request.getHeaders().getContentType();
        log.info("This Content-Type : {}", contentType);
        return request.getBody();
    }

    @PostMapping("/responseEntity")
    public ResponseEntity<Event> getResponseEntity(@RequestBody @Valid Event event,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(event);
    }
}
