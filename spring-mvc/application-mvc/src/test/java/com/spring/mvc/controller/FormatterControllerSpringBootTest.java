package com.spring.mvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @SpringBootTest - 해당 어노테이션은 @ExtendWith(SpringExtension.class) 를 메타 어노테이션으로 사용하기 때문에 jUnit5 에서 별도로 @ExtendWith 어노테이션을 추가하지 않아도 된다.
 * - @SpringBootApplication 테스트를 위해 사용된다. 즉, @SpringBootApplication 이 붙언 클래스를 찾아 Spring Application Context 를 시작하도록 한다.
 * - 테스트를 하고자 하는 애플리케이션의 모든 Bean 을 등록한다.
 * - 이 때, 등록된 Bean 객체에 대한 Mock 객체를 사용하기 위해서는 @AutoConfigureMockMvc 어노테이션을 통해 사용할 수 있다.
 */
@SpringBootTest
@AutoConfigureMockMvc
class FormatterControllerSpringBootTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void formatter() throws Exception {
        mockMvc.perform(get("/formatter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Beautiful World!"));
    }
}