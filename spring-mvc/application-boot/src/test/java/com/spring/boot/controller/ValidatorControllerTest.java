package com.spring.boot.controller;

import com.spring.boot.valid.EventValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {
        ValidatorController.class,
        EventValidator.class
})
class ValidatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEvent() throws Exception {
        // EventValidator 테스트
        mockMvc.perform(get("/validator")
                        .param("name", "Bad Event"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}