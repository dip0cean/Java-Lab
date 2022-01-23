package com.spring.solid.srp.company.bc;

import com.spring.solid.srp.payment.LocalPaymentService;
import org.springframework.stereotype.Service;

@Service
public class NewBcLocalPaymentService implements LocalPaymentService {

    @Override
    public String payByLocal() {
        return "BC카드 :: 국내 가맹점 결제 승인 (SRP)";
    }
}
