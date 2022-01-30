package com.spring.solid.ocp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.solid.ocp.domain.PaymentResponse;
import com.spring.solid.ocp.domain.PaymentType;
import com.spring.solid.ocp.payment.legacy.LegacyPaymentService;
import com.spring.solid.ocp.payment.refactor.DefaultPaymentService;
import com.spring.solid.ocp.payment.refactor.KakaoPayPaymentService;
import com.spring.solid.ocp.payment.refactor.PaymentProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {
        OcpController.class,
        LegacyPaymentService.class,
        DefaultPaymentService.class,
        KakaoPayPaymentService.class,
        PaymentProxy.class
})
class OcpControllerTest {

    private MockMvc mockMvc;

    private final PaymentResponse paymentResponse = PaymentResponse.builder().buyerName("또꼬망").totalPrice(15000).build();

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(System.out::println)
                .build();
    }

    @Test
    void legacy() throws Exception {
        paymentResponse.setPaymentType(PaymentType.NAVER_PAY);

        mockMvc.perform(get("/ocp/legacy")
                        .content(mapper.writeValueAsString(paymentResponse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void refactor() throws Exception {
        paymentResponse.setPaymentType(PaymentType.KAKAO_PAY);

        mockMvc.perform(get("/ocp/refactor")
                        .content(mapper.writeValueAsString(paymentResponse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}