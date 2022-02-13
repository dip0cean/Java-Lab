package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getSessionTest() throws Exception {
        mockMvc.perform(get("/session")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/index"))
                .andExpect(model().attributeExists("user"))
                .andExpect(request().sessionAttribute("user", notNullValue()));
    }

    @Test
    public void getSessionAttributesTest() throws Exception {
        // @SessionAttribute 를 통해 users 를 세션에 등록
        mockMvc.perform(get("/sessionAttributes")
                        .param("id", "1")
                        .param("age", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/index"))
                .andExpect(model().attributeExists("users"))
                .andExpect(request().sessionAttribute("users", notNullValue()));

        // 아규먼트로 SessionStatus 를 받아 세션 처리를 완료로 변경한다.
        mockMvc.perform(post("/setComplete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/index"))
                .andExpect(request().sessionAttribute("users", nullValue()));
    }
}