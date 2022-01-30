package com.spring.solid.ocp.payment.refactor;

import com.spring.solid.ocp.domain.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@RequiredArgsConstructor
public class PaymentProxy implements PaymentService {

    private final DefaultPaymentService defaultPaymentService;
    private final KakaoPayPaymentService kakaoPayPaymentService;

    public String payment(PaymentResponse paymentResponse) {
        switch (paymentResponse.getPaymentType()) {
            case DEFAULT:
                return defaultPaymentService.payment(paymentResponse);
            case KAKAO_PAY:
                return kakaoPayPaymentService.payment(paymentResponse);
            default:
                return paymentResponse.getPaymentType() + "은(는) 지원하지 않는 결제수단입니다.";
        }
    }
}
