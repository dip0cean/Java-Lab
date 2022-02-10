package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {
        UriArgumentController.class,
        // EventFormatter.class
        // EventConverter.class
})
class UriArgumentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEventTest() throws Exception {
        mockMvc.perform(get("/uri/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    void getEventOptionalTest() throws Exception {
        mockMvc.perform(get("/optional"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("2"));
    }
}