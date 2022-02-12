package com.spring.boot.controller;

import com.spring.boot.util.converter.EventConverter;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {
        RequestController.class,
        EventConverter.class
})
class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("@RequestParam 테스트")
    public void getRequestParamTest() throws Exception {
        mockMvc.perform(get("/request")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"));
    }

    @Test
    @DisplayName("@RequestParam Map<String, String> 바인딩 테스트")
    public void getRequestParamsTest() throws Exception {

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
    @DisplayName("@RequestParam(required = true) 테스트 > BadRequest")
    public void getRequiredTest() throws Exception {
        mockMvc.perform(get("/request/required")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("@ModelAttribute 테스트")
    public void getModelTest() throws Exception {
        mockMvc.perform(get("/modelAttribute")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"));
    }

    @Test
    @DisplayName("BindingResult 및 @Valid 테스트")
    public void getModelByBindingResultTest() throws Exception {
        String str = "ErrorTest";
        mockMvc.perform(get("/modelAttribute/bindingResult")
                        .param("id", "1")
                        .param("order", str))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'order'; nested exception is java.lang.NumberFormatException: For input string: \"%s\"", str)));
    }

    @Test
    @DisplayName("@Validated 및 ValidGroup 테스트")
    public void validTest() throws Exception {
        mockMvc.perform(get("/validated")
                        .param("id", "1")
                        .param("age", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0]").value("must be greater than or equal to 0"))
                .andExpect(jsonPath("[1]").value("must not be null"));
    }
}