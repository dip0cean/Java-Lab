package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlPatternController.class)
class UrlPatternControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception {

        // /?
        mockMvc.perform(get("/questionMark/a"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("한 글자만 경로로 인식합니다."))
                .andExpect(handler().handlerType(UrlPatternController.class))
                .andExpect(handler().methodName("questionMark"));

        // /*
        mockMvc.perform(get("/asterisk/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("/ 뒤의 한 단어만 경로로 인식합니다."))
                .andExpect(handler().handlerType(UrlPatternController.class))
                .andExpect(handler().methodName("asterisk"));

        // /*/{path} 의 경우 맵핑하지 못합니다.
        mockMvc.perform(get("/asterisk/hello/peace"))
                .andDo(print())
                .andExpect(status().isNotFound());

        // /** 에 포함하는 모든 경로를 맵핑합니다.
        mockMvc.perform(get("/asterisks/hello/peace"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("/ 뒤의 여러 Path 를 경로로 인식합니다."))
                .andExpect(handler().handlerType(UrlPatternController.class))
                .andExpect(handler().methodName("asterisks"));

        // 경로가 중복되는 경우 더 구체적으로 설정된 핸들러로 맵핑됩니다.
        mockMvc.perform(get("/asterisk/love/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("경로가 중복되는 경우 더 구체적인 핸들러를 선택합니다."))
                .andExpect(handler().handlerType(UrlPatternController.class))
                .andExpect(handler().methodName("love"));
    }
}