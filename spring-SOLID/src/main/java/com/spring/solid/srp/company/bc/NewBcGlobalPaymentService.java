package com.spring.solid.srp.company.bc;

import com.spring.solid.srp.payment.GlobalPaymentService;
import org.springframework.stereotype.Service;

@Service
public class NewBcGlobalPaymentService implements GlobalPaymentService {

    @Override
    public String payByGlobal() {
        return "BC카드 :: 해외 가맹점 결제 승인 (SRP)";
    }
}
