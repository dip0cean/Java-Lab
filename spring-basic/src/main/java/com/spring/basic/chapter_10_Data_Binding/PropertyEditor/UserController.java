package com.spring.basic.chapter_10_Data_Binding.PropertyEditor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(User.class, new UserEditor());
    }

    @GetMapping("/user/{user}")
    public String getUser(@PathVariable User user) {
        log.info("User? :: {}", user);
        return user.getId().toString();
    }
}
