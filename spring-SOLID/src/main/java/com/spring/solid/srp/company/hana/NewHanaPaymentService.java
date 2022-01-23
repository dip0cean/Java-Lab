package com.spring.solid.srp.company.hana;

import com.spring.solid.srp.payment.LocalPaymentService;
import org.springframework.stereotype.Service;

@Service
public class NewHanaPaymentService implements LocalPaymentService {

    @Override
    public String payByLocal() {
        return "하나카드 :: 국내 가맹점 결제 승인 (SRP)";
    }
}
