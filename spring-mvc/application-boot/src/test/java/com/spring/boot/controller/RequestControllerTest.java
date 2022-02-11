package com.spring.boot.controller;

import com.spring.boot.util.converter.EventConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {
        RequestController.class,
        EventConverter.class
})
class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRequestParamTest() throws Exception {
        mockMvc.perform(get("/request")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"));
    }

    @Test
    public void getRequestParams() throws Exception {

        Map<String, String> languages = new HashMap<>();
        languages.put(Locale.ENGLISH.getLanguage(), "hello");
        languages.put(Locale.KOREAN.getLanguage(), "안녕");

        MultiValueMap<String, String> params = new MultiValueMapAdapter<>(new HashMap<>());
        params.setAll(languages);

        mockMvc.perform(get("/request/map")
                        .params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(Locale.ENGLISH.getLanguage()).value("hello"))
                .andExpect(jsonPath(Locale.KOREAN.getLanguage()).value("안녕"));
    }

    @Test
    public void getRequired() throws Exception {
        mockMvc.perform(get("/request/required")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}