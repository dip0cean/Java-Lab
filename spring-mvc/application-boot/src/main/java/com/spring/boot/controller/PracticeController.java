package com.spring.boot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PracticeController {

    @GetMapping("/events")
    public String get() {
        return "get";
    }

    @GetMapping("/events/{path}")
    public String getPath(@PathVariable("path") String path) {
        return path;
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String post() {
        return "post";
    }

    @DeleteMapping("/events/{path}")
    public String deletePath(@PathVariable("path") String path) {
        return "Delete " + path;
    }

    @PutMapping(value = "/events/{path}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String putPath(@PathVariable("path") String path) {
        return "Put " + path;
    }
}
