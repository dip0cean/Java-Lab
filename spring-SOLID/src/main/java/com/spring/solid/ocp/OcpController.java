package com.spring.solid.ocp;

import com.spring.solid.ocp.domain.PaymentResponse;
import com.spring.solid.ocp.payment.legacy.LegacyPaymentService;
import com.spring.solid.ocp.payment.refactor.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/ocp")
@RequiredArgsConstructor
public class OcpController {

    private final LegacyPaymentService legacyPaymentService;
    private final PaymentService paymentService;

    @GetMapping("/legacy")
    public ResponseEntity<String> legacy(@RequestBody PaymentResponse paymentResponse) {
        switch (paymentResponse.getPaymentType()) {
            case DEFAULT:
                return ResponseEntity.ok(legacyPaymentService.payment(paymentResponse));
            case KAKAO_PAY:
                return ResponseEntity.ok(legacyPaymentService.kakaoPayment(paymentResponse));
            default:
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(paymentResponse.getPaymentType() + "은(는) 지원하지 않는 결제수단입니다.");
        }
    }

    @GetMapping("/refactor")
    public ResponseEntity<String> refactor(@RequestBody PaymentResponse paymentResponse) {
        return ResponseEntity.ok(paymentService.payment(paymentResponse));
    }
}
