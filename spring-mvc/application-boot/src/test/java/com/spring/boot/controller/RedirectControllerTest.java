package com.spring.boot.controller;

import com.spring.boot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RedirectController.class)
class RedirectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRedirectTest() throws Exception {
        mockMvc.perform(get("/redirect"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/redirect?id=1&name=newEvent"));
    }

    @Test
    public void getErrorTest() throws Exception {
        mockMvc.perform(get("/redirect/error"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/redirect/user?id=1&name=John"));
    }

    @Test
    public void getFlashTest() throws Exception {
        User user = User
                .builder()
                .id("2")
                .name("김퍼피")
                .build();

        mockMvc.perform(get("/redirect/flash")
                .flashAttr("user", user))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/redirect/user"));
    }
}