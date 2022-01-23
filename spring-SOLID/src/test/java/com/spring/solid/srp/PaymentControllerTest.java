package com.spring.solid.srp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.solid.srp.domain.CardDto;
import com.spring.solid.srp.domain.CardType;
import com.spring.solid.srp.payment.CardTypeFactory;
import com.spring.solid.srp.payment.GlobalPaymentService;
import com.spring.solid.srp.payment.LocalPaymentService;
import com.spring.solid.srp.payment.OldPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {
        PaymentController.class,
        OldPaymentService.class,
        LocalPaymentService.class,
        GlobalPaymentService.class,
        CardTypeFactory.class
})
class PaymentControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    private static final CardDto.Request bcCard = CardDto.Request
            .builder()
            .cardNum("0123-4567-8901-2345")
            .cvc("001")
            .expirationDate(LocalDateTime.now().plusYears(5))
            .limitAmount(10000000)
            .cardType(CardType.BC)
            .build();
    private static final CardDto.Request hanaCard = CardDto.Request
            .builder()
            .cardNum("5678-9012-3456-7890")
            .cvc("002")
            .expirationDate(LocalDateTime.now().plusYears(5))
            .limitAmount(40000000)
            .cardType(CardType.HANA)
            .build();
    private static final CardDto.Request wooriCard = CardDto.Request
            .builder()
            .cardNum("1234-5678-9012-3456")
            .cvc("003")
            .expirationDate(LocalDateTime.now().plusYears(5))
            .limitAmount(80000000)
            .cardType(CardType.WOORI)
            .build();

    /**
     * 인코딩 설정
     */
    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(System.out::println)
                .build();
    }

    /**
     * 국내 가맹점 결제 테스트 (SRP 미적용)
     *
     * @throws Exception Exception
     */
    @Test
    void payByLocal() throws Exception {
        // BC카드 결제 테스트
        mockMvc.perform(post("/pay/local")
                        .content(objectMapper.writeValueAsString(bcCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("BC카드 :: 국내 가맹점 결제 승인"));

        // 하나카드 결제 테스트
        mockMvc.perform(post("/pay/local")
                        .content(objectMapper.writeValueAsString(hanaCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("하나카드 :: 국내 가맹점 결제 승인"));

        // 우리카드 결제 테스트
        mockMvc.perform(post("/pay/local")
                        .content(objectMapper.writeValueAsString(wooriCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("해당 카드는 결제 서비스를 지원하지 않습니다."));
    }

    /**
     * 해외 가맹점 결제 테스트 (SRP 미적용)
     *
     * @throws Exception Exception
     */
    @Test
    void payByGlobal() throws Exception {
        // BC카드 결제 테스트
        mockMvc.perform(post("/pay/global")
                        .content(objectMapper.writeValueAsString(bcCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("BC카드 :: 해외 가맹점 결제 승인"));

        // 하나카드 결제 테스트
        mockMvc.perform(post("/pay/global")
                        .content(objectMapper.writeValueAsString(hanaCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("하나카드 :: 해외 가맹점은 결제 서비스를 지원하지 않습니다."));

        // 우리카드 결제 테스트
        mockMvc.perform(post("/pay/global")
                        .content(objectMapper.writeValueAsString(wooriCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("해당 카드는 결제 서비스를 지원하지 않습니다."));
    }

    /**
     * 국내 가맹점 결제 테스트 (SRP 적용)
     *
     * @throws Exception Exception
     */
    @Test
    void payByLocal_SRP() throws Exception {
        // BC카드 결제 테스트
        mockMvc.perform(post("/pay/local/srp")
                        .content(objectMapper.writeValueAsString(bcCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("BC카드 :: 국내 가맹점 결제 승인 (SRP)"));

        // 하나카드 결제 테스트
        mockMvc.perform(post("/pay/local/srp")
                        .content(objectMapper.writeValueAsString(hanaCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("하나카드 :: 국내 가맹점 결제 승인 (SRP)"));

        // 우리카드 결제 테스트
        mockMvc.perform(post("/pay/local/srp")
                        .content(objectMapper.writeValueAsString(wooriCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("해당 카드는 결제 서비스를 지원하지 않습니다."));
    }

    /**
     * 해외 가맹점 결제 테스트 (SRP 적용)
     *
     * @throws Exception Exceptions
     */
    @Test
    void payByGlobal_SRP() throws Exception {
        // BC카드 결제 테스트
        mockMvc.perform(post("/pay/global/srp")
                        .content(objectMapper.writeValueAsString(bcCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("BC카드 :: 해외 가맹점 결제 승인 (SRP)"));

        // 하나카드 결제 테스트
        mockMvc.perform(post("/pay/global/srp")
                        .content(objectMapper.writeValueAsString(bcCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("BC카드 :: 해외 가맹점 결제 승인 (SRP)"));

        // 우리카드 결제 테스트
        mockMvc.perform(post("/pay/global/srp")
                        .content(objectMapper.writeValueAsString(hanaCard))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("해당 카드는 해외 결제를 지원하지 않습니다."));
    }
}