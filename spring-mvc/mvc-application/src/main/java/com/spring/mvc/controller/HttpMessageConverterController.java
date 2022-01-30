package com.spring.mvc.controller;

import com.spring.mvc.model.Dog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController // -> 해당 객체 내부에 존재하는 모든 핸들러 메소드에 @ResponseBody 적용되도록 한다.
@Controller
public class HttpMessageConverterController {

    @GetMapping("/httpMessage")
    public ResponseEntity<Dog> httpMessage(@RequestBody Dog dog) {
        return ResponseEntity.ok(dog);
    }
}
