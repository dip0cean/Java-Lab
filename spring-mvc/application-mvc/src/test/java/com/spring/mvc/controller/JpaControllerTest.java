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
class JpaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DogRepository dogRepository;

    @Test
    void requestParam() throws Exception {
        Dog dog = Dog.builder().name("또꼬망").build();
        Dog tmpDog = dogRepository.save(dog);

        mockMvc.perform(get("/jpa").param("id", tmpDog.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("I love you, 또꼬망"));
    }
}