package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PracticeController.class)
class PracticeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void Question_1() throws Exception {
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("get"))
                .andExpect(handler().handlerType(PracticeController.class))
                .andExpect(handler().methodName("get"));
    }

    @Test
    public void Question_2() throws Exception {
        mockMvc.perform(get("/events/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andExpect(handler().handlerType(PracticeController.class))
                .andExpect(handler().methodName("getPath"));
    }

    @Test
    public void Question_3() throws Exception {
        mockMvc.perform(get("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("post"))
                .andExpect(handler().handlerType(PracticeController.class))
                .andExpect(handler().methodName("post"));
    }

    @Test
    public void Question_4() throws Exception {
        mockMvc.perform(delete("/events/bye"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Delete bye"))
                .andExpect(handler().handlerType(PracticeController.class))
                .andExpect(handler().methodName("deletePath"));
    }

    @Test
    public void Question_5() throws Exception {
        mockMvc.perform(put("/events/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Put edit"))
                .andExpect(handler().handlerType(PracticeController.class))
                .andExpect(handler().methodName("putPath"));
    }
}