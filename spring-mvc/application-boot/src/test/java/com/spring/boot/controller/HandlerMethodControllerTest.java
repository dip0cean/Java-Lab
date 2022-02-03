package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HandlerMethodController.class)
class HandlerMethodControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * HTTP Method 요청 테스트
     *
     * @throws Exception Exception
     */
    @Test
    public void requestMappingTest() throws Exception {
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello!"));
    }

    /**
     * 핸들러의 HTTP Method 와 맞지 않는 요청을 보냈을 때 응답하는 405 에러 확인 테스트
     *
     * @throws Exception Exception
     */
    @Test
    public void exceptionTest() throws Exception {
        mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }
}