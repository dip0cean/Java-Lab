package com.spring.solid.ocp.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PaymentResponse {
    // 결제자 성함
    private String buyerName;
    // 최종 결제 금액
    private Integer totalPrice;
    // 결제 수단
    private PaymentType paymentType;
}
