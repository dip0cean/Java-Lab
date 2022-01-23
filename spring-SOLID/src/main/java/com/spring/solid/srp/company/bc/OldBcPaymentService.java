package com.spring.solid.srp.company.bc;

import com.spring.solid.srp.payment.OldPaymentService;
import org.springframework.stereotype.Service;

@Service
public class OldBcPaymentService implements OldPaymentService {
    @Override
    public String payByLocal() {
        return "BC카드 :: 국내 가맹점 결제 승인";
    }

    @Override
    public String payByGlobal() {
        return "BC카드 :: 해외 가맹점 결제 승인";
    }
}
