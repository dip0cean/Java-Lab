package com.spring.solid.ocp.payment.refactor;

import com.spring.solid.ocp.domain.PaymentResponse;

public interface PaymentService {
    String payment(PaymentResponse paymentResponse);
}
