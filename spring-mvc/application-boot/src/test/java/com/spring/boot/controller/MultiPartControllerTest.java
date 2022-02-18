package com.spring.boot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultiPartController.class)
class MultiPartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void uploadTest() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello Spring!".getBytes());

        mockMvc.perform(multipart("/upload").file(file))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}