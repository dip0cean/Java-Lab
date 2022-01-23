package com.spring.solid.srp.company.hana;

import com.spring.solid.srp.payment.OldPaymentService;
import org.springframework.stereotype.Service;

@Service
public class OldHanaPaymentService implements OldPaymentService {
    @Override
    public String payByLocal() {
        return "하나카드 :: 국내 가맹점 결제 승인";
    }

    @Override
    public String payByGlobal() {
        // 하나카드사는 해외 결제를 지원하지 않는다.
        return "하나카드 :: 해외 가맹점은 결제 서비스를 지원하지 않습니다.";
    }
}
