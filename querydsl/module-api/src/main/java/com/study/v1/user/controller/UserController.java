package com.study.v1.user.controller;

import com.study.domain.entity.User;
import com.study.v1.user.service.UserApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserApiService userApiService;

    @GetMapping()
    public ResponseEntity<User> getUser(@RequestParam("id") long id) {
        User user = userApiService.findUser(id);
        return ResponseEntity.ok(user);
    }
}
