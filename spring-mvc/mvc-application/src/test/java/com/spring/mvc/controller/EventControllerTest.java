package com.spring.mvc.controller;

import com.spring.mvc.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest - Controller 계층을 테스트하기 위해서 사용되는 어노테이션이다.
 * - @ExtensionWith(SpringExtension.class) 를 메타 어노테이션으로 사용하기 때문에 별도로 @ExtensionWith(SpringExtension.class) 를 추가하지 않아도 된다.
 * - 테스트하고자 하는 Controller 클래스에서 주입받고 있는 Bean 객체가 있다면 value 로 설정해줘야 한다.
 */
@WebMvcTest(value = {
        EventController.class,
        EventService.class
})
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void events() throws Exception {
        mockMvc.perform(get("/"))
                .andExpectAll(status().isOk());
    }
}