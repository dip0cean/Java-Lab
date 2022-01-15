package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({
        DogFormatter.class,
        DogController.class
})
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDogDto() throws Exception {
        mockMvc.perform(get("/dogDto/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("또익"));
    }

    @Test
    public void getDog() throws Exception {
        mockMvc.perform(get("/dog/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("또꼬망"));
    }
}