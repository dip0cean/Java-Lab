package com.spring.mvc.controller;

import com.spring.mvc.model.Dog;
import com.spring.mvc.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InterceptorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DogRepository dogRepository;

    @Test
    public void interceptor() throws Exception {
        Dog kkomang = Dog.builder().name("꼬망").build();
        Dog ddoick = Dog.builder().name("또익").build();

        kkomang = dogRepository.save(kkomang);
        ddoick = dogRepository.save(ddoick);

        // GreetingInterceptor Test
        mockMvc.perform(get("/interceptor/greeting")
                        .param("id", kkomang.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("good boy, " + kkomang.getName()));

        // AnotherInterceptor Test
        mockMvc.perform(get("/interceptor/another")
                        .param("id", ddoick.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("good girl, " + ddoick.getName()));
    }
}