package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(ModelController.class)
class ModelControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/model/getUsers"))
                .andDo(print())
                .andExpect(jsonPath("[0].name").value("짱구"));

        mockMvc.perform(get("/model/getEvent"))
                .andDo(print())
                .andExpect(jsonPath("name").value("족제비 in Unhappy World"));

        mockMvc.perform(get("/model/getModel"))
                .andDo(print())
                .andExpect(model().attributeExists("users", "event", "locale"));
    }
}