package com.spring.solid.srp;

import com.spring.solid.srp.domain.CardDto;
import com.spring.solid.srp.payment.CardTypeFactory;
import com.spring.solid.srp.payment.GlobalPaymentService;
import com.spring.solid.srp.payment.LocalPaymentService;
import com.spring.solid.srp.payment.OldPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/pay")
@RequiredArgsConstructor
public class PaymentController {

    private final CardTypeFactory cardTypeFactory;

    /**
     * 국내 결제 - SRP 미적용
     *
     * @return String
     */
    @PostMapping("/local")
    public ResponseEntity<String> payByLocal(@RequestBody CardDto.Request request) {
        try {
            OldPaymentService paymentService = cardTypeFactory.getOldPaymentService(request);
            String message = paymentService.payByLocal();

            log.info("Class Type? {}", paymentService.getClass());
            log.info("Result Message : {}", message);

            return ResponseEntity.ok(message);
        } catch (NoSuchMethodException e) {
            return ResponseEntity.badRequest().body("해당 카드는 결제 서비스를 지원하지 않습니다.");
        }
    }

    /**
     * 해외 결제 - SRP 미적용
     *
     * @return String
     */
    @PostMapping("/global")
    public ResponseEntity<String> payByGlobal(@RequestBody CardDto.Request request) {
        try {
            OldPaymentService paymentService = cardTypeFactory.getOldPaymentService(request);
            String message = paymentService.payByGlobal();

            log.info("Class Type? {}", paymentService.getClass());
            log.info("Result Message : {}", message);

            return ResponseEntity.ok(message);
        } catch (NoSuchMethodException e) {
            return ResponseEntity.badRequest().body("해당 카드는 결제 서비스를 지원하지 않습니다.");
        }
    }

    /**
     * 국내 결제 - SRP 적용
     *
     * @return String
     */
    @PostMapping("/local/srp")
    public ResponseEntity<String> payByLocal_SRP(@RequestBody CardDto.Request request) {
        try {
            LocalPaymentService localPaymentService = cardTypeFactory.getLocalPaymentService(request);
            String message = localPaymentService.payByLocal();

            log.info("Class Type? {}", localPaymentService.getClass());
            log.info("Result Message : {}", message);

            return ResponseEntity.ok(message);
        } catch (NoSuchMethodException e) {
            return ResponseEntity.badRequest().body("해당 카드는 결제 서비스를 지원하지 않습니다.");
        }
    }

    /**
     * 해외 결제 - SRP 적용
     *
     * @return String
     */
    @PostMapping("/global/srp")
    public ResponseEntity<String> payByGlobal_SRP(@RequestBody CardDto.Request request) {
        try {
            GlobalPaymentService globalPaymentService = cardTypeFactory.globalPaymentService(request);
            String message = globalPaymentService.payByGlobal();

            log.info("Class Type? {}", globalPaymentService.getClass());
            log.info("Result Message : {}", message);

            return ResponseEntity.ok(message);
        } catch (NoSuchMethodException e) {
            return ResponseEntity.badRequest().body("해당 카드는 해외 결제를 지원하지 않습니다.");
        }
    }

}
