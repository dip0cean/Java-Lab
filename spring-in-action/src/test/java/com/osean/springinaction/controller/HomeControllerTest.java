package com.osean.springinaction.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Spring MVC Application 형태로 테스트를 진행하도록 한다.
// 즉, 해당 어노테이션을 통해 HomeController 클래스가 Spring MVC 에 등록되므로 웹 요청을 보낼 수 있게된다.
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    // 실제 서버를 실행하는 대신 Spring MVC 의 Mocking 매커니즘을 사용하도록 한다.
    // MockMvc 객체를 자동으로 주입한다.
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // 응답은 반드시 HTTP 200 OK 상태가 되어야 한다.
                .andExpect(view().name("home")) // View 이름은 반드시 "home" 이어야 한다다.
                .andExpect(content().string(containsString("Welcome My World!"))); // 브라우저에 보이는 뷰에는 반드시 해당 텍스트가 포함되어야 한다.
    }
}