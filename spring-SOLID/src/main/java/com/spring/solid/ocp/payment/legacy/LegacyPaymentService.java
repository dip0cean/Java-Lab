package com.spring.solid.ocp.payment.legacy;

import com.spring.solid.ocp.domain.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class LegacyPaymentService {

    public String payment(PaymentResponse paymentResponse) {
        return String.format("%s 님, %s 원 결제 완료되었습니다.", paymentResponse.getBuyerName(), paymentResponse.getTotalPrice());
    }

    public String kakaoPayment(PaymentResponse paymentResponse) {
        return String.format("%s 님, %s 로 %s 원 결제 완료되었습니다.", paymentResponse.getBuyerName(), paymentResponse.getPaymentType(), paymentResponse.getTotalPrice());
    }
}
