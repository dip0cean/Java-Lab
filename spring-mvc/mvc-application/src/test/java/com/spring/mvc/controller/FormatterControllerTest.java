package com.spring.mvc.controller;

import com.spring.mvc.formatter.PersonFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {
        FormatterController.class,
        PersonFormatter.class
})
class FormatterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void pathVariable() throws Exception {
        mockMvc.perform(get("/formatter/pathVariable/SeongHeon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, SeongHeon! Nice to meet you!"));
    }

    @Test
    void requestParam() throws Exception {
        mockMvc.perform(get("/formatter/requestParam").param("name", "SeongHeon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("SeongHeon is your brother?"));
    }
}