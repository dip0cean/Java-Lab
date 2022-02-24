package com.spring.boot.controller;

import com.spring.boot.domain.Event;
import com.spring.boot.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/api")
public class ResponseEntityApi {

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler(CustomException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("custom exception..");
    }

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
